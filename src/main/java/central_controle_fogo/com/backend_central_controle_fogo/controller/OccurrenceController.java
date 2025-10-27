package central_controle_fogo.com.backend_central_controle_fogo.controller;



import central_controle_fogo.com.backend_central_controle_fogo.dto.occurrenceReport.OccurrenceDispatchDTO;
import central_controle_fogo.com.backend_central_controle_fogo.dto.occurrenceReport.OccurrenceOnSiteDTO;
import central_controle_fogo.com.backend_central_controle_fogo.dto.occurrenceReport.OccurrenceResponseDTO;
import central_controle_fogo.com.backend_central_controle_fogo.service.occurrenceService.OccurrenceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/occurrences")
public class OccurrenceController {

    @Autowired
    private OccurrenceService occurrenceService;

    @GetMapping
    public ResponseEntity<List<OccurrenceResponseDTO>> getAllOccurrences() {
        return ResponseEntity.ok(occurrenceService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OccurrenceResponseDTO> getOccurrenceById(@PathVariable Long id) {
        return ResponseEntity.ok(occurrenceService.findById(id));
    }

    //Etapa 1
    @PostMapping
    public ResponseEntity<OccurrenceResponseDTO> createOccurrence(
            @Valid @RequestBody OccurrenceDispatchDTO dto) {
        OccurrenceResponseDTO createdOccurrence = occurrenceService.createOccurrence(dto);
        return new ResponseEntity<>(createdOccurrence, HttpStatus.CREATED);
    }

    // Etapa 2
    @PutMapping("/{id}/on-site-report")
    public ResponseEntity<OccurrenceResponseDTO> addOnSiteReport(
            @PathVariable Long id,
            @Valid @RequestBody OccurrenceOnSiteDTO dto) {
        OccurrenceResponseDTO updatedOccurrence = occurrenceService.addOnSiteReport(id, dto);
        return ResponseEntity.ok(updatedOccurrence);
    }

    @PutMapping("/deactivate/{id}")
    public ResponseEntity deactivateOccurrence(@PathVariable Long id) {
        try{
            if(id < 1){
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }
            var service = occurrenceService.deactivate(id);
            if (!service){
                return new ResponseEntity("Ocorrência não encontrada",HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>("Ocorrência desativada com sucesso.", HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/activate/{id}")
    public ResponseEntity activateOccurrence(@PathVariable Long id) {
        try{
            if(id < 1){
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }
            var service = occurrenceService.activate(id);
            if (!service){
                return new ResponseEntity("Ocorrência não encontrada",HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>("Ocorrência ativada com sucesso.", HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}