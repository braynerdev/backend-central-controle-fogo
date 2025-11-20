package central_controle_fogo.com.backend_central_controle_fogo.service.occurrenceService;

import central_controle_fogo.com.backend_central_controle_fogo.dto.auth.UserPaginatorDTO;
import central_controle_fogo.com.backend_central_controle_fogo.dto.generic.PaginatorGeneric;
import central_controle_fogo.com.backend_central_controle_fogo.dto.generic.ResponseDTO;
import central_controle_fogo.com.backend_central_controle_fogo.dto.occurrenceReport.OccurrencePaginatorDTO;
import central_controle_fogo.com.backend_central_controle_fogo.dto.occurrenceReport.OccurrenceUpdateDTO;
import central_controle_fogo.com.backend_central_controle_fogo.dto.occurrenceReport.SimpleIdNameDTO;
import central_controle_fogo.com.backend_central_controle_fogo.dto.occurrenceReport.OccurrenceInfoMapDTO;
import central_controle_fogo.com.backend_central_controle_fogo.dto.occurrenceReport.occurrenceFirst.OccurrenceFirstRequestDTO;
import central_controle_fogo.com.backend_central_controle_fogo.dto.occurrenceReport.occurrenceFirst.OccurrenceFirstResponseDTO;
import central_controle_fogo.com.backend_central_controle_fogo.dto.occurrenceReport.occurrenceSecond.OccurrenceSecondRequestDTO;
import central_controle_fogo.com.backend_central_controle_fogo.dto.occurrenceReport.occurrenceSecond.OccurrenceSecondResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IOccurrenceService {
    public OccurrenceFirstResponseDTO registerOccurence(OccurrenceFirstRequestDTO occurrenceFirstRequestDTO);
    public OccurrenceSecondResponseDTO registerComplement(OccurrenceSecondRequestDTO occurrenceSecondRequestDTO);
    public OccurrenceSecondResponseDTO getOccurrenceById(Long id);
    public OccurrenceSecondResponseDTO updateOccurrence(Long id, OccurrenceUpdateDTO occurrenceUpdateDTO);
    public ResponseDTO activate(Long id);
    public ResponseDTO deactivate(Long id);
    PaginatorGeneric<OccurrencePaginatorDTO> GetPaginatorGetPaginatorOccurrence(Pageable pageable, boolean active, String filterGeneric);
    List<SimpleIdNameDTO> getAllOccurrenceNatures();
    List<SimpleIdNameDTO> getAllOccurrenceTypes();
    List<SimpleIdNameDTO> getAllOccurrenceSubTypes();
    List<SimpleIdNameDTO> getAllOccurrenceStatuses();
    List<OccurrenceInfoMapDTO> getInfoMap();
}
