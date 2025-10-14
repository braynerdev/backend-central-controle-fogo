package central_controle_fogo.com.backend_central_controle_fogo.service;

import central_controle_fogo.com.backend_central_controle_fogo.dto.auth.CadastreRequestDTO;
import central_controle_fogo.com.backend_central_controle_fogo.dto.generic.ResponseDTO;

public interface IAuthService {
    ResponseDTO Cadastre(CadastreRequestDTO cadastreRequestDTO);
}
