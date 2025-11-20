package central_controle_fogo.com.backend_central_controle_fogo.controller;


import central_controle_fogo.com.backend_central_controle_fogo.dto.generic.PaginatorGeneric;
import central_controle_fogo.com.backend_central_controle_fogo.dto.patent.PatentResquestDTO;
import central_controle_fogo.com.backend_central_controle_fogo.dto.vehicle.VehicleRequestDTO;
import central_controle_fogo.com.backend_central_controle_fogo.service.battalion.BattalionService;
import central_controle_fogo.com.backend_central_controle_fogo.service.patent.PatentService;
//import central_controle_fogo.com.backend_central_controle_fogo.service.vehicle.VehicleService;
import central_controle_fogo.com.backend_central_controle_fogo.service.vehicle.VehicleService;
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
@RequestMapping("api/vehicle")
@Tag(name = "Veículo", description = "Operações relacionadas a veículos")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping()
    @Operation(summary = "Criar veículo")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR')")
    public ResponseEntity<?> createdVehicle(@Valid @RequestBody VehicleRequestDTO vehicleRequestDTO) {
        try{
            var service =  vehicleService.createdVehicle(vehicleRequestDTO);
            if(service == null){
                return ResponseEntity.badRequest().build();
            }
            return ResponseEntity.ok(service);
        }
        catch (Exception ex){
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Editar veículo")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR')")
    public ResponseEntity<?> putVehicle(@Valid @RequestBody VehicleRequestDTO vehicleRequestDTO, @RequestParam Long id){
        var service =  vehicleService.updateVehicle(id, vehicleRequestDTO);
        if(service == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(service);
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Buscar veículo por ID")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'AGENTE', 'OBSERVADOR')")
    public ResponseEntity<?> getVehicle(@RequestParam Long id){
        var service =  vehicleService.getByIdVehicle(id);
        if(service == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(service);
    }

    @PatchMapping(value = "deactivate/{id}")
    @Operation(summary = "Desativar veículo")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR')")
    public ResponseEntity deactivate(@PathVariable Long id) {
        try{
            if (id == null || id < 1) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            var deactivateVehicle = vehicleService.DeactivateVehicle(id);
            if(!deactivateVehicle.isSucesso()){
                return new ResponseEntity<>(deactivateVehicle.getMensagem(),HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(deactivateVehicle.getMensagem(),HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>("Erro ao desativar veícuo!",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping(value = "activate/{id}")
    @Operation(summary = "Ativar veículo")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR')")
    public ResponseEntity activate(@PathVariable Long id){
        try{
            if (id == null || id < 1) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            var service = vehicleService.ActivateVehicle(id);

            return service.isSucesso() ? new ResponseEntity<>(service.getMensagem(),HttpStatus.OK) : new ResponseEntity<>(service.getMensagem(),HttpStatus.BAD_REQUEST);
        }
        catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/all")
    @Operation(summary = "Listar todos os veículos")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'AGENTE', 'OBSERVADOR')")
    public ResponseEntity<?> getAllVehicles(){
        try{
            var service = vehicleService.getAll();
            if(service == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok(service);
        }
        catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/paginator")
    @Operation(summary = "Buscar veículos paginados")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'AGENTE', 'OBSERVADOR')")
    public ResponseEntity<PaginatorGeneric> getPaginatorPatent(@RequestParam(defaultValue = "1") int page,
                                                               @RequestParam(defaultValue = "10") int size,
                                                               @RequestParam(required = false) String filterGeneric,
                                                               @RequestParam(defaultValue = "true") boolean active){
        try {
            Pageable pageable  = PageRequest.of(page - 1, size);
            var service = vehicleService.GetPaginatorVehicle(pageable, active, filterGeneric);

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
