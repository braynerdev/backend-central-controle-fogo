package central_controle_fogo.com.backend_central_controle_fogo.service.auth;

import central_controle_fogo.com.backend_central_controle_fogo.dto.auth.*;
import central_controle_fogo.com.backend_central_controle_fogo.dto.generic.PaginatorGeneric;
import central_controle_fogo.com.backend_central_controle_fogo.dto.generic.ResponseDTO;
import central_controle_fogo.com.backend_central_controle_fogo.dto.generic.ResponseListMessageDTO;
import central_controle_fogo.com.backend_central_controle_fogo.model.patent.Patent;
import central_controle_fogo.com.backend_central_controle_fogo.model.auth.User;
import central_controle_fogo.com.backend_central_controle_fogo.model.battalion.Battalion;
import central_controle_fogo.com.backend_central_controle_fogo.model.generic.Address;
import central_controle_fogo.com.backend_central_controle_fogo.repository.auth.IRepositoryUser;
import central_controle_fogo.com.backend_central_controle_fogo.repository.battalion.IBattalionRepository;
import central_controle_fogo.com.backend_central_controle_fogo.repository.patent.IPatentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.security.SecureRandom;
import java.util.Base64;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthService implements IAuthService {

    @Autowired
    private IRepositoryUser userRepository;
    @Autowired
    private IBattalionRepository battalionRepository;
    @Autowired
    private IPatentRepository patentRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtEncoder jwtEncoder;
    @Autowired
    private ModelMapper modelMapper;



    @Transactional
    @Override
    public UserInfoDTO getById(Long id) {
        try {
            var user = userRepository.findById(id).orElse(null);
            var userInfoDTO = modelMapper.map(user, UserInfoDTO.class);
            return userInfoDTO;
        }
        catch (Exception ex) {
            return null;
        }

    }

    @Transactional
    @Override
    public ResponseDTO deactivateUser(Long id) {
        try{
            var user = userRepository.findById(id).orElse(null);
            if (user == null) {
                return ResponseDTO.erro("usuário não encontrado");
            }
            user.setActive(false);
            user.setRefreshTokenExpiration(null);
            user.setRefreshToken(null);
            var salvo = userRepository.save(user);
            return salvo != null ? ResponseDTO.sucesso("usuário atualizado com sucesso!")  : ResponseDTO.erro("Erro ao desativar usuário!");
        }
        catch (Exception e){
            return ResponseDTO.erro("Erro ao desativar usuário!");
        }
    }

    @Transactional
    @Override
    public boolean logout(Long id){
        try{
            var user = userRepository.findById(id).orElse(null);
            if (user == null) {
                return false;
            }
            user.setRefreshTokenExpiration(null);
            user.setRefreshToken(null);
            var salvo = userRepository.save(user);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    @Transactional
    @Override
    public ResponseDTO activateUser(Long id) {
        try {
            var repository = userRepository.findById(id).orElse(null);
            if (repository == null) {
                return ResponseDTO.erro("Usuário não existente!");
            }
            if (repository.isActive()){
                return ResponseDTO.erro("Usuário já esta ativo!");
            }
            repository.setActive(true);
            userRepository.save(repository);
            return ResponseDTO.sucesso("Usuário atualizado com sucesso!");
        }
        catch (Exception e){
            return ResponseDTO.erro("Erro ao ativar usuário");
        }
    }

    @Transactional
    @Override
    public String refreshToken(RefreshTokenRequestDTO refreshTokenRequestDTO) {
        try {
            var user = userRepository.findByUsername(refreshTokenRequestDTO.getUsername()).orElse(null);
            System.out.println("User: " + user);
            if (user == null) {
                return null;
            }
            else if(!user.isActive()){
                return null;
            }
            else if(user.getRefreshTokenExpiration().isBefore(OffsetDateTime.now())) {
                return null;
            }

            var gerarToken = generateToken(user);
            System.out.println("Token: " + gerarToken);
            if(gerarToken == null){
                return null;
            };

            return gerarToken;

        }
        catch (Exception ex) {
            return null;
        }
    }


    @Transactional
    @Override
    public ResponseListMessageDTO CreatedUser(CadastreRequestDTO dto) {
        try {

            Address address = modelMapper.map(dto.getAddress(), Address.class);
            Battalion battalion = battalionRepository.findById(dto.getBattalion()).orElse(null);
            Patent patent = patentRepository.findById(dto.getPatent()).orElse(null);

            User user = CadastreRequestDTO.mapDto(dto);
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
            user.setAddress(address);
            user.setBattalion(battalion);
            user.setPatent(patent);

            var userSave = userRepository.save(user);
            return ResponseListMessageDTO.sucesso(List.of("Usuário criado com sucesso!"));
        } catch (Exception e) {
            return ResponseListMessageDTO.erro(List.of("Erro ao criar usuário: " + e.getMessage()));
        }
    }

    @Transactional
    @Override
    public String generateToken(User user) {
        try {
            var now = Instant.now();
            var expiresIn = 3600;
            var scopes = user.getRoles();

            var claims = JwtClaimsSet.builder()
                    .issuer("central_controle_fogo")
                    .subject(user.getId().toString())
                    .issuedAt(now)
                    .expiresAt(now.plusSeconds(expiresIn))
                    .claim("scope", scopes)
                    .build();

            var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
            return jwtValue;
        }
        catch (Exception ex) {
            return null;
        }
    }

    @Transactional
    @Override
    public User getByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    @Transactional
    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        try{
            var user = userRepository.findByUsername(loginRequest.getUsername()).orElse(null);


            if(user == null){
                return new LoginResponse(false, "Usuário inixistente");
            }

            if (user.isLoginCorrect(loginRequest, passwordEncoder)) {
                return new LoginResponse(false, "Username ou senha iválido");
            }


            if(!user.isActive()){
                System.out.println("Erro ao autenticar");
                return new LoginResponse(false, "Usuário não está ativo!");
            }

            var token = generateToken(user);

            SecureRandom secureRandom = new SecureRandom();
            Base64.Encoder base64Encoder = Base64.getUrlEncoder().withoutPadding();

            byte[] randomBytes = new byte[64];
            secureRandom.nextBytes(randomBytes);
            var refreshToken = base64Encoder.encodeToString(randomBytes);



            var expiredRefreshToken = OffsetDateTime.now().plusSeconds(2678400);

            user.setRefreshToken(refreshToken);
            user.setRefreshTokenExpiration(expiredRefreshToken);
            userRepository.save(user);
            var responseUser = modelMapper.map(user, UserResponseDTO.class);

            return new LoginResponse(true, "Login efetuado com sucesso.", token, refreshToken, expiredRefreshToken, responseUser);
        }
        catch (Exception e){
            System.out.println("Erro ao autenticar: " + e.getMessage());
            return null;
        }

    }

    @Transactional
    @Override
    public PaginatorGeneric<UserPaginatorDTO> GetPaginatorUser(Pageable pageable, boolean active, String filterGeneric) {
        Page<User> paginator = userRepository.findAll(pageable);

        List<UserPaginatorDTO> filterList = paginator.stream()
                .map(u -> modelMapper.map(u, UserPaginatorDTO.class))
                .filter(u -> {
                    boolean matches = true;
                    if (filterGeneric != null && !filterGeneric.isEmpty()) {
                        matches = u.getMatriculates().contains(filterGeneric) || u.getNormalizedName().contains(filterGeneric.toUpperCase());
                    }
                    matches &= u.isActive() == active;
                    return matches;
                })
                .collect(Collectors.toList());

        var paginatorGeneric = new PaginatorGeneric<UserPaginatorDTO>(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                filterList.size(),
                filterList
        );
        return paginatorGeneric;
    }


}
