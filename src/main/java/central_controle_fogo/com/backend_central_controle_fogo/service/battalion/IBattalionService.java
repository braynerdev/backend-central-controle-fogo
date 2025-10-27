package central_controle_fogo.com.backend_central_controle_fogo.service.battalion;


import central_controle_fogo.com.backend_central_controle_fogo.dto.battalion.BattalionRequestDTO;
import central_controle_fogo.com.backend_central_controle_fogo.dto.battalion.BattalionResponseDTO;
import central_controle_fogo.com.backend_central_controle_fogo.dto.battalion.BattalionResponsePaginatorDTO;
import central_controle_fogo.com.backend_central_controle_fogo.dto.generic.PaginatorGeneric;
import central_controle_fogo.com.backend_central_controle_fogo.dto.generic.ResponseDTO;
import org.springframework.data.domain.Pageable;

public interface IBattalionService {

    ResponseDTO CreateBattalion(BattalionRequestDTO dto);
    BattalionResponseDTO GetBattalionById(Long id);
    ResponseDTO UpdateBattalion(Long id, BattalionRequestDTO dto);
    PaginatorGeneric<BattalionResponsePaginatorDTO> GetPaginatorBattalion(Pageable pageable,  String name, boolean active);
    boolean deactivateBattalion(Long id);
    boolean activateBattalion(Long id);
}
