package central_controle_fogo.com.backend_central_controle_fogo.controller;


import central_controle_fogo.com.backend_central_controle_fogo.dto.auth.CadastreRequestDTO;
import central_controle_fogo.com.backend_central_controle_fogo.model.auth.User;
import central_controle_fogo.com.backend_central_controle_fogo.service.AuthService;
import central_controle_fogo.com.backend_central_controle_fogo.service.IAuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@Tag(name = "Auth", description = "Operações relacionadas ao usuário")
public class AuthController {

    @Autowired
    private AuthService authService ;

    @GetMapping(value = "/{id}")
    @Operation(summary = "Buscar cliente por ID")
    public ResponseEntity getById(@PathVariable Long id) {
        try{
            if (id == null || id < 1) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            var pegarUser = this.authService.getById(id);
            return pegarUser == null ? new ResponseEntity<>("Usuário não encontrado!",HttpStatus.NOT_FOUND) : new ResponseEntity<>(pegarUser, HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping(value = "/auth/deactivate/{id}")
    @Operation(summary = "Desativar cliente pelo id")
    public ResponseEntity deactivate(@PathVariable Long id) {
        try{
            if (id == null || id < 1) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            var deactivateUser = this.authService.deactivateUser(id);
            if(!deactivateUser.isSucesso()){
                return new ResponseEntity<>(deactivateUser.getMensagem(),HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(deactivateUser.getMensagem(),HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>("Erro ao desativar usuário!",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    @Operation(summary = "Criar usuário")
    public ResponseEntity registerUser(@Valid @RequestBody CadastreRequestDTO cadastreRequestDTO) {
        try{
            if (cadastreRequestDTO == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
