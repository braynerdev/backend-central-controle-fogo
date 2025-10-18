package central_controle_fogo.com.backend_central_controle_fogo.service.battalion;

import central_controle_fogo.com.backend_central_controle_fogo.dto.auth.CadastreRequestDTO;
import central_controle_fogo.com.backend_central_controle_fogo.dto.battalion.BattalionRequestDTO;
import central_controle_fogo.com.backend_central_controle_fogo.dto.generic.ResponseDTO;

public interface IBattalionService {

    ResponseDTO CreateBattalion(BattalionRequestDTO dto);
}
