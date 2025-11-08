package central_controle_fogo.com.backend_central_controle_fogo.controller;

import central_controle_fogo.com.backend_central_controle_fogo.dto.occurrenceReport.occurrenceFirst.OccurrenceFirstRequestDTO;
import central_controle_fogo.com.backend_central_controle_fogo.dto.occurrenceReport.occurrenceSecond.OccurrenceSecondRequestDTO;
import central_controle_fogo.com.backend_central_controle_fogo.service.occurrenceService.OccurrenceService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
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
   public ResponseEntity<?> registerOccurence(@Valid @RequestBody OccurrenceFirstRequestDTO occurrence){
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

    @PatchMapping()
    @Operation(summary = "Cadastrar dados complementares ocorrência")
    public ResponseEntity<?> registerComplement(@Valid @RequestBody OccurrenceSecondRequestDTO occurrence){
        try {
            var service = occurrenceService.registerComplement(occurrence);
            if (service == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok().body(service);
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(value = "{id}")
    @Operation(summary = "Pegar ocorrência pelo id")
    public ResponseEntity<?> registerComplement(@PathVariable Long id){
       try {
            var service = occurrenceService.getOccurrenceById(id);
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