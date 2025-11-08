package central_controle_fogo.com.backend_central_controle_fogo.controller;


import central_controle_fogo.com.backend_central_controle_fogo.dto.battalion.BattalionRequestDTO;
import central_controle_fogo.com.backend_central_controle_fogo.dto.generic.PaginatorGeneric;
import central_controle_fogo.com.backend_central_controle_fogo.service.battalion.BattalionService;
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
@RequestMapping("api/battalion")
@Tag(name = "battalion", description = "Operações relacionadas a batalhão")
public class BattalionController {

    @Autowired
    private BattalionService battalionService;


    @PostMapping(value = "/created")
    @Operation(summary = "criar batalhão")
    public ResponseEntity createBattalion(@Valid @RequestBody BattalionRequestDTO battalionRequestDTO ) {
        try {
            if (battalionRequestDTO == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            var serviceBattalion = battalionService.CreateBattalion(battalionRequestDTO);

            if (!serviceBattalion.isSucesso()) {
                return new ResponseEntity<>(serviceBattalion.getMensagem(), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(serviceBattalion.getMensagem(), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping(value = "/{id}")
    @Operation(summary = "Pegar batalhão")
    public ResponseEntity getBattalion(@PathVariable() Long id) {
        try {
            if (id == null || id < 1) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            var service = battalionService.GetBattalionById(id);
            if (service == null) {
                return new ResponseEntity<>("Não existe nenhum batalhão salvo com esse id",HttpStatus.NOT_FOUND);
            }
            System.out.println(service);
            return new ResponseEntity<>(service, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Editar Batalhão")
    public ResponseEntity putBattalion(@PathVariable() Long id, @Valid @RequestBody BattalionRequestDTO battalionRequestDTO) {
        try {
            if (id == null || id < 1) {
                return new ResponseEntity<>("Id inválido",HttpStatus.BAD_REQUEST);
            }
            if (battalionRequestDTO == null) {
                return new ResponseEntity<>("Dados do batalhão inválido",HttpStatus.BAD_REQUEST);
            }
            var service = battalionService.UpdateBattalion(id,battalionRequestDTO);
            if (!service.isSucesso()) {
                return new ResponseEntity<>(service.getMensagem(), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(service.getMensagem(), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/paginator")
    @Operation(summary = "Pegar batalhões paginados")
    public ResponseEntity<PaginatorGeneric> getBattalionPaginator(@RequestParam(defaultValue = "1") int page,
                                                                  @RequestParam(defaultValue = "10") int size,
                                                                  @RequestParam(required = false) String name,
                                                                  @RequestParam(defaultValue = "true") boolean active) {
        try {
            Pageable pageable  = PageRequest.of(page - 1, size);
            var service = battalionService.GetPaginatorBattalion(pageable, name, active);

            if (service == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(service, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PatchMapping(value = "/deactivate/{id}")
    @Operation(summary = "Desativar usuário.")
    public ResponseEntity deactivateBattalion(@RequestParam Long id){
        try{
            if  (id == null || id < 1) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            var service = battalionService.deactivateBattalion(id);
            return service ? new ResponseEntity("Batalhão desativado com sucesso.",HttpStatus.OK) : new ResponseEntity<>("Batalhão não existe ou já está desativado.",HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping(value = "/activate/{id}")
    @Operation(summary = "Ativar batalhão")
    public ResponseEntity activateBattalion(@RequestParam Long id){
        try{
            if  (id == null || id < 1) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            var service = battalionService.activateBattalion(id);
            return service ? new ResponseEntity("Batalhão ativado com sucesso.",HttpStatus.OK) : new ResponseEntity<>("Batalhão não existe ou já está ativado", HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
