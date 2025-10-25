package central_controle_fogo.com.backend_central_controle_fogo.validation;

import central_controle_fogo.com.backend_central_controle_fogo.dto.auth.CadastreRequestDTO;

import java.util.List;

public interface IRegisterUserValidation {
    public List<String> registerUserValidation(CadastreRequestDTO dto);
}
