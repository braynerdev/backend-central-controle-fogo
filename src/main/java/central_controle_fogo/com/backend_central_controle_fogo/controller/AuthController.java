package central_controle_fogo.com.backend_central_controle_fogo.controller;


import central_controle_fogo.com.backend_central_controle_fogo.dto.auth.CadastreRequestDTO;
import central_controle_fogo.com.backend_central_controle_fogo.service.IAuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {

    private final IAuthService authService;

    public AuthController(IAuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = "/cadastre")
    public ResponseEntity<String> cadastre(@RequestBody @Valid CadastreRequestDTO cadastreRequestDTO) {

        if (cadastreRequestDTO == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        var cadastreService = this.authService.Cadastre(cadastreRequestDTO);

    }
}
