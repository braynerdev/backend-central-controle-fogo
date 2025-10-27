package central_controle_fogo.com.backend_central_controle_fogo.controller;


import central_controle_fogo.com.backend_central_controle_fogo.dto.generic.PaginatorGeneric;
import central_controle_fogo.com.backend_central_controle_fogo.dto.patent.PatentResquestDTO;
import central_controle_fogo.com.backend_central_controle_fogo.service.patent.PatentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/patent")
@Tag(name = "Patent", description = "Operações relacionadas a patente")
public class PatentController {

    @Autowired
    private PatentService patentService;

    @PostMapping(value = "/register/patent")
    @Operation(summary = "Criar patente")
    public ResponseEntity<?> createdPatent(@Valid @RequestBody PatentResquestDTO  patent){
        var service =  patentService.createdPatent(patent);
        if(service == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(service);
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Editar patente")
    public ResponseEntity<?> putPatente(@Valid @RequestBody PatentResquestDTO  patent, @RequestParam Long id){
        var service =  patentService.updatePatent(id, patent);
        if(service == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(service);
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Pegar patente pelo id")
    public ResponseEntity<?> getPatente(@RequestParam Long id){
        var service =  patentService.getByIdPatent(id);
        if(service == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(service);
    }

    @PutMapping(value = "deactivate/{id}")
    @Operation(summary = "Desativar usuário pelo id")
    public ResponseEntity deactivate(@PathVariable Long id) {
        try{
            if (id == null || id < 1) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            var deactivateUser = patentService.DeactivatePatent(id);
            if(!deactivateUser.isSucesso()){
                return new ResponseEntity<>(deactivateUser.getMensagem(),HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(deactivateUser.getMensagem(),HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>("Erro ao desativar usuário!",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "activate/{id}")
    @Operation(summary = "Ativar o usuário.")
    public ResponseEntity activate(@PathVariable Long id){
        try{
            if (id == null || id < 1) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            var service = patentService.ActivatePatent(id);

            return service.isSucesso() ? new ResponseEntity<>(service.getMensagem(),HttpStatus.OK) : new ResponseEntity<>(service.getMensagem(),HttpStatus.BAD_REQUEST);
        }
        catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping(value = "/paginator")
    @Operation(summary = "Pegar patentes paginadas")
    public ResponseEntity<PaginatorGeneric> getPaginatorPatent(@RequestParam(defaultValue = "1") int page,
                                                               @RequestParam(defaultValue = "10") int size,
                                                               @RequestParam(required = false) String filterGeneric,
                                                               @RequestParam(defaultValue = "true") boolean active){
        try {
            Pageable pageable  = PageRequest.of(page - 1, size);
            var service = patentService.GetPaginatorPatent(pageable, active, filterGeneric);

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
