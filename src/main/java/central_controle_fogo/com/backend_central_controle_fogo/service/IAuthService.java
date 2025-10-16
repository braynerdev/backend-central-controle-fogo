package central_controle_fogo.com.backend_central_controle_fogo.service;

import central_controle_fogo.com.backend_central_controle_fogo.dto.auth.CadastreRequestDTO;
import central_controle_fogo.com.backend_central_controle_fogo.dto.generic.ResponseDTO;
import central_controle_fogo.com.backend_central_controle_fogo.model.auth.User;
import jakarta.validation.Valid;

public interface IAuthService {
    User getById(Long id) ;
    ResponseDTO deactivateUser(Long id);
}
