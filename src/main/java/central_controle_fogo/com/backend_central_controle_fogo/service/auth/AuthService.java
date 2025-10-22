package central_controle_fogo.com.backend_central_controle_fogo.service.auth;

import central_controle_fogo.com.backend_central_controle_fogo.dto.auth.CadastreRequestDTO;
import central_controle_fogo.com.backend_central_controle_fogo.dto.auth.LoginRequest;
import central_controle_fogo.com.backend_central_controle_fogo.dto.auth.LoginResponse;
import central_controle_fogo.com.backend_central_controle_fogo.dto.generic.ResponseDTO;
import central_controle_fogo.com.backend_central_controle_fogo.model.auth.Patent;
import central_controle_fogo.com.backend_central_controle_fogo.model.auth.User;
import central_controle_fogo.com.backend_central_controle_fogo.model.battalion.Battalion;
import central_controle_fogo.com.backend_central_controle_fogo.model.generic.Address;
import central_controle_fogo.com.backend_central_controle_fogo.repository.auth.IRepositoryUser;
import central_controle_fogo.com.backend_central_controle_fogo.repository.battalion.IBattalionRepository;
import central_controle_fogo.com.backend_central_controle_fogo.repository.patent.IPatent;
import org.apache.catalina.Role;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.stream.Collectors;

@Service
public class AuthService implements IAuthService {

    @Autowired
    private IRepositoryUser userRepository;
    @Autowired
    private IBattalionRepository battalionRepository;
    @Autowired
    private IPatent patentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtEncoder jwtEncoder;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public User getById(Long id) {
        try {
            return userRepository.findById(id).orElse(null);
        }
        catch (Exception ex) {
            return null;
        }

    }

    @Override
    public ResponseDTO deactivateUser(Long id) {
        try{
            var user = userRepository.findById(id).orElse(null);
            if (user == null) {
                return ResponseDTO.erro("usuário não encontrado");
            }
            user.setActive(false);
            var salvo = userRepository.save(user);
            return salvo != null ? ResponseDTO.sucesso("usuário atualizado com sucesso!")  : ResponseDTO.erro("Erro ao desativar usuário!");
        }
        catch (Exception e){
            return ResponseDTO.erro("Erro ao desativar usuário!");
        }
    }

    @Transactional
    @Override
    public ResponseDTO CreatedUser(CadastreRequestDTO dto) {
        try {
            Address address = modelMapper.map(dto.getAddress(), Address.class);

            Battalion battalion = battalionRepository.findById(dto.getBattalion())
                    .orElse(null);
            if (battalion == null) {
                return ResponseDTO.erro("Batalhão não encontrado");
            }
            Patent patent = patentRepository.findById(dto.getPatent())
                    .orElse(null);
            if (patent == null) {
                return ResponseDTO.erro("Patente não encontrada");
            }

            User user = CadastreRequestDTO.mapDto(dto);
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
            user.setAddress(address);
            user.setBattalion(battalion);
            user.setPatent(patent);

            var userSave = userRepository.save(user);

            if (userSave == null) {
                return ResponseDTO.erro("Batalhão não encontrado");
            }
            return ResponseDTO.sucesso("Usuário criado com sucesso!");
        } catch (Exception e) {
            return ResponseDTO.erro("Erro ao criar usuário: " + e.getMessage());
        }
    }

    @Override
    public String generateToken(LoginRequest loginRequest,long timeExpired) {
        var user = userRepository.findByUsername(loginRequest.getUsername());

        if (user.isEmpty() || !user.get().isLoginCorrect(loginRequest, passwordEncoder)) {
            throw new BadCredentialsException("user or password is invalid!");
        }

        var now = Instant.now();
        long expiresIn = timeExpired;

//        var scopes = user.get().getRoles()
//                .stream()
//                .map(Role::getRolename)
//                .collect(Collectors.joining(" "));

        var claims = JwtClaimsSet.builder()
                .issuer("central_controle_fogo")
                .subject(user.get().getId().toString())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiresIn))
//                .claim("scope", scopes)
                .build();

        var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
        return jwtValue;
    }

    public User getByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }
}
