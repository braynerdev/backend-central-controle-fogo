package central_controle_fogo.com.backend_central_controle_fogo.controller;


import central_controle_fogo.com.backend_central_controle_fogo.dto.auth.CadastreRequestDTO;
import central_controle_fogo.com.backend_central_controle_fogo.model.auth.User;
import central_controle_fogo.com.backend_central_controle_fogo.service.AuthService;
import central_controle_fogo.com.backend_central_controle_fogo.service.IAuthService;
import jakarta.validation.Valid;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private AuthService authService ;

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> getById(@PathVariable Long id) {
        try{
            if (id == null || id < 1) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            var pegarUser = this.authService.getById(id);
            return pegarUser == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(pegarUser, HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping(value = "/auth/deactivate/{id}")
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
            return new ResponseEntity<>("Erro ao desativar usuário!",HttpStatus.BAD_REQUEST);
        }
    }
}
