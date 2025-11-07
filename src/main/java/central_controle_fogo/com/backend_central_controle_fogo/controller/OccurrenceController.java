package central_controle_fogo.com.backend_central_controle_fogo.controller;

import central_controle_fogo.com.backend_central_controle_fogo.dto.occurrenceReport.OccurrenceRequestDTO;
import central_controle_fogo.com.backend_central_controle_fogo.model.occurrenceReport.Occurrence;
import central_controle_fogo.com.backend_central_controle_fogo.service.occurrenceService.OccurrenceService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/occurrences")
public class OccurrenceController {

    @Autowired
    private OccurrenceService occurrenceService;

   @PostMapping()
   @Operation(summary = "Cadastrar ocorrência.")
   public ResponseEntity<?> registerOccurence(@RequestBody OccurrenceRequestDTO occurrence){
        try {
            var service = occurrenceService.registerOccurence(occurrence);
            if (service == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok().body(service);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
   }
}