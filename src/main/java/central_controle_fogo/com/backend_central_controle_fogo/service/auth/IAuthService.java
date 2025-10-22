package central_controle_fogo.com.backend_central_controle_fogo.service.auth;

import central_controle_fogo.com.backend_central_controle_fogo.dto.auth.CadastreRequestDTO;
import central_controle_fogo.com.backend_central_controle_fogo.dto.auth.LoginRequest;
import central_controle_fogo.com.backend_central_controle_fogo.dto.auth.LoginResponse;
import central_controle_fogo.com.backend_central_controle_fogo.dto.generic.ResponseDTO;
import central_controle_fogo.com.backend_central_controle_fogo.model.auth.User;

public interface IAuthService {
    User getById(Long id);
    ResponseDTO deactivateUser(Long id);
    ResponseDTO CreatedUser(CadastreRequestDTO cadastreRequestDTO);
    String generateToken(LoginRequest loginRequest, long timeExpired);
    User getByUsername(String username);
}
