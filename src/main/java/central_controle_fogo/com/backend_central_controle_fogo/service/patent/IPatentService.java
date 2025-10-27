package central_controle_fogo.com.backend_central_controle_fogo.service.patent;

import central_controle_fogo.com.backend_central_controle_fogo.dto.auth.UserPaginatorDTO;
import central_controle_fogo.com.backend_central_controle_fogo.dto.generic.PaginatorGeneric;
import central_controle_fogo.com.backend_central_controle_fogo.dto.generic.ResponseDTO;
import central_controle_fogo.com.backend_central_controle_fogo.dto.patent.PatentResponseDTO;
import central_controle_fogo.com.backend_central_controle_fogo.dto.patent.PatentResquestDTO;
import central_controle_fogo.com.backend_central_controle_fogo.model.patent.Patent;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPatentService{
    PatentResponseDTO createdPatent(PatentResquestDTO patentResquestDTO);
    PatentResponseDTO updatePatent(Long id,PatentResquestDTO patentResquestDTO);
    PatentResponseDTO getByIdPatent(Long id);
    PaginatorGeneric<PatentResponseDTO> GetPaginatorPatent(Pageable pageable, boolean active, String filterGeneric);
    ResponseDTO DeactivatePatent(Long id);
    ResponseDTO ActivatePatent(Long id);
}
