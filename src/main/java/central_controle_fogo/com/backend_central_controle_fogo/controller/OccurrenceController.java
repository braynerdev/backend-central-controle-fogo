package central_controle_fogo.com.backend_central_controle_fogo.controller;



import central_controle_fogo.com.backend_central_controle_fogo.dto.generic.PaginatorGeneric;
import central_controle_fogo.com.backend_central_controle_fogo.dto.occurrenceReport.OccurrenceOnSiteDTO;
import central_controle_fogo.com.backend_central_controle_fogo.dto.occurrenceReport.OccurrenceResponseDTO;
import central_controle_fogo.com.backend_central_controle_fogo.dto.occurrenceReport.OccurrenceUpdateDTO;
import central_controle_fogo.com.backend_central_controle_fogo.dto.occurrenceReport.OcurrenceRequestDTO;
import central_controle_fogo.com.backend_central_controle_fogo.service.occurrenceService.OccurrenceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/occurrences")
public class OccurrenceController {

    @Autowired
    private OccurrenceService occurrenceService;

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id) {
        try {
            if (id == null || id < 1) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            var occurrence = this.occurrenceService.getById(id);
            return occurrence == null ? new ResponseEntity<>("Ocorrência não encontrada!", HttpStatus.NOT_FOUND) : new ResponseEntity<>(occurrence, HttpStatus.OK);
        }
        catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<?> createOccurrence(
            @Valid @RequestBody OcurrenceRequestDTO dto) {
        try{
            boolean success = occurrenceService.createOccurrence(dto);
            if(!success){
                return new ResponseEntity<>("Erro ao criar ocorrência", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>("Ocorrência criada com sucesso", HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/complete/{id}")
    public ResponseEntity<?> completeOccurrence(
            @PathVariable Long id,
            @Valid @RequestBody OccurrenceOnSiteDTO dto) {
        try {
            if(id < 1){
                return new ResponseEntity<>("ID inválido", HttpStatus.BAD_REQUEST);
            }
            boolean success = occurrenceService.completeOccurrence(id, dto);
            if(!success){
                return new ResponseEntity<>("Erro ao completar ocorrência", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>("Ocorrência completada com sucesso", HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>("Erro: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/deactivate/{id}")
    public ResponseEntity deactivateOccurrence(@PathVariable Long id) {
        try {
            if (id == null || id < 1) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            var deactivateOccurrence = occurrenceService.deactivateOccurrence(id);
            if (!deactivateOccurrence.isSucesso()) {
                return new ResponseEntity<>(deactivateOccurrence.getMensagem(), HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(deactivateOccurrence.getMensagem(), HttpStatus.OK);
        }
        catch (Exception ex) {
            return new ResponseEntity<>("Erro ao desativar ocorrência!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/activate/{id}")
    public ResponseEntity activateOccurrence(@PathVariable Long id) {
        try {
            if (id == null || id < 1) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            var service = occurrenceService.activateOccurrence(id);
            return service.isSucesso() ? new ResponseEntity<>(service.getMensagem(), HttpStatus.OK) : new ResponseEntity<>(service.getMensagem(), HttpStatus.BAD_REQUEST);
        }
        catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOccurrence(
            @PathVariable Long id,
            @Valid @RequestBody OccurrenceUpdateDTO dto) {
        try {
            if (id == null || id < 1) {
                return new ResponseEntity<>("ID inválido", HttpStatus.BAD_REQUEST);
            }
            
            var response = occurrenceService.updateOccurrence(id, dto);
            
            if (!response.isSucesso()) {
                return new ResponseEntity<>(response.getMensagem(), HttpStatus.BAD_REQUEST);
            }
            
            return new ResponseEntity<>(response.getMensagem(), HttpStatus.OK);
        }
        catch (Exception ex) {
            return new ResponseEntity<>("Erro ao atualizar ocorrência: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/paginator")
    public ResponseEntity<PaginatorGeneric> getOccurrencePaginator(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String filterGeneric,
            @RequestParam(defaultValue = "true") boolean active) {
        try {
            Pageable pageable = PageRequest.of(page - 1, size);
            var service = occurrenceService.GetPaginatorOccurrence(pageable, active, filterGeneric);

            if (service == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(service, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}