package central_controle_fogo.com.backend_central_controle_fogo.controller;

import central_controle_fogo.com.backend_central_controle_fogo.dto.generic.PaginatorGeneric;
import central_controle_fogo.com.backend_central_controle_fogo.dto.occurrenceReport.OccurrenceUpdateDTO;
import central_controle_fogo.com.backend_central_controle_fogo.dto.occurrenceReport.occurrenceFirst.OccurrenceFirstRequestDTO;
import central_controle_fogo.com.backend_central_controle_fogo.dto.occurrenceReport.occurrenceSecond.OccurrenceSecondRequestDTO;
import central_controle_fogo.com.backend_central_controle_fogo.service.occurrenceService.OccurrenceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/occurrences")
@Tag(name = "Ocorrência", description = "Operações relacionadas a ocorrências")
public class OccurrenceController {

    @Autowired
    private OccurrenceService occurrenceService;

   @PostMapping(value = "/register")
   @Operation(summary = "Cadastrar ocorrência (primeira etapa)")
   @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'AGENTE')")
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

    @PatchMapping(value = "/complement")
    @Operation(summary = "Cadastrar dados complementares da ocorrência (segunda etapa)")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'AGENTE')")
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
    @Operation(summary = "Buscar ocorrência por ID")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'AGENTE', 'OBSERVADOR')")
    public ResponseEntity<?> getOccurrenceById(@PathVariable Long id){
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

    @PutMapping(value = "/{id}")
    @Operation(summary = "Atualizar ocorrência")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<?> updateOccurrence(@PathVariable Long id, @Valid @RequestBody OccurrenceUpdateDTO occurrenceUpdateDTO){
        try {
            var service = occurrenceService.updateOccurrence(id, occurrenceUpdateDTO);
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

    @PatchMapping(value = "/activate/{id}")
    @Operation(summary = "Ativar ocorrência")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<?> activate(@PathVariable Long id){
        try {
            var service = occurrenceService.activate(id);
            if (service == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok().body(service);
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @PatchMapping(value = "/deactivate/{id}")
    @Operation(summary = "Desativar ocorrência")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<?> deactivate(@PathVariable Long id){
        try {
            var service = occurrenceService.deactivate(id);
            if (service == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok().body(service);
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(value = "/paginator")
    @Operation(summary = "Buscar ocorrências paginadas")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'AGENTE', 'OBSERVADOR')")
    public ResponseEntity<PaginatorGeneric> getOccurrencePaginator(@RequestParam(defaultValue = "1") int page,
                                                                   @RequestParam(defaultValue = "10") int size,
                                                                   @RequestParam(required = false) String filterGeneric,
                                                                   @RequestParam(defaultValue = "true") boolean active) {
        try {
            Pageable pageable = PageRequest.of(page - 1, size);
            var service = occurrenceService.GetPaginatorGetPaginatorOccurrence(pageable, active, filterGeneric);

            if (service == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(service, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/natures")
    @Operation(summary = "Listar todas as naturezas de ocorrência (id e nome)")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'AGENTE', 'OBSERVADOR')")
    public ResponseEntity<?> getAllNatures() {
        try {
            var natures = occurrenceService.getAllOccurrenceNatures();
            return ResponseEntity.ok(natures);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(value = "/types")
    @Operation(summary = "Listar todos os tipos de ocorrência (id e nome)")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'AGENTE', 'OBSERVADOR')")
    public ResponseEntity<?> getAllTypes() {
        try {
            var types = occurrenceService.getAllOccurrenceTypes();
            return ResponseEntity.ok(types);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(value = "/subtypes")
    @Operation(summary = "Listar todos os subtipos de ocorrência (id e nome)")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'AGENTE', 'OBSERVADOR')")
    public ResponseEntity<?> getAllSubTypes() {
        try {
            var subTypes = occurrenceService.getAllOccurrenceSubTypes();
            return ResponseEntity.ok(subTypes);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(value = "/status")
    @Operation(summary = "Listar todos os status de ocorrência (id e nome)")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'AGENTE', 'OBSERVADOR')")
    public ResponseEntity<?> getAllStatuses() {
        try {
            var statuses = occurrenceService.getAllOccurrenceStatuses();
            return ResponseEntity.ok(statuses);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(value = "/infomap")
    @Operation(summary = "Listar informações das ocorrências para mapa")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'AGENTE', 'OBSERVADOR')")
    public ResponseEntity<?> getInfoMap() {
        try {
            var infoMap = occurrenceService.getInfoMap();
            return ResponseEntity.ok(infoMap);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}