package central_controle_fogo.com.backend_central_controle_fogo.service;

import central_controle_fogo.com.backend_central_controle_fogo.dto.auth.CadastreRequestDTO;
import central_controle_fogo.com.backend_central_controle_fogo.dto.generic.ResponseDTO;
import central_controle_fogo.com.backend_central_controle_fogo.model.auth.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class AuthService implements IAuthService {

    private final IAuthRepository authRepository;

    @Autowired
    public AuthService(IAuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResponseDTO Cadastre(CadastreRequestDTO cadastreRequestDTO) {
        try {
            User user = modelMapper.map(cadastreRequestDTO, User.class);
            var repository =  authRepository.Cadastre(user);

        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseDTO.newResponseDTO(false, e.getMessage());
        }
    }
}
