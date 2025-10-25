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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOccurrence(@PathVariable Long id) {
        occurrenceService.delete(id);
        return ResponseEntity.noContent().build();
    }
}