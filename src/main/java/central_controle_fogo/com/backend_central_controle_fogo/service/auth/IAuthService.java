package central_controle_fogo.com.backend_central_controle_fogo.service.auth;

import central_controle_fogo.com.backend_central_controle_fogo.dto.auth.*;
import central_controle_fogo.com.backend_central_controle_fogo.dto.battalion.BattalionResponsePaginatorDTO;
import central_controle_fogo.com.backend_central_controle_fogo.dto.generic.PaginatorGeneric;
import central_controle_fogo.com.backend_central_controle_fogo.dto.generic.ResponseDTO;
import central_controle_fogo.com.backend_central_controle_fogo.dto.generic.ResponseListMessageDTO;
import central_controle_fogo.com.backend_central_controle_fogo.model.auth.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IAuthService {
    UserInfoDTO getById(Long id);
    ResponseDTO deactivateUser(Long id);
    ResponseDTO activateUser(Long id);
    ResponseListMessageDTO CreatedUser(CadastreRequestDTO cadastreRequestDTO);
    String generateToken(User user);
    String refreshToken(RefreshTokenRequestDTO refreshTokenRequestDTO);
    User getByUsername(String username);
    LoginResponse login(LoginRequest loginRequest);
    public boolean logout(Long id);
    PaginatorGeneric<UserPaginatorDTO> GetPaginatorUser(Pageable pageable, boolean active, String filterGeneric);
    List<UserSimpleDTO> GetAllUsers();
}
