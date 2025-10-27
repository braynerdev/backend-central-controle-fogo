package central_controle_fogo.com.backend_central_controle_fogo.controller;


import central_controle_fogo.com.backend_central_controle_fogo.dto.auth.*;
import central_controle_fogo.com.backend_central_controle_fogo.dto.generic.PaginatorGeneric;
import central_controle_fogo.com.backend_central_controle_fogo.service.auth.IAuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/auth")
@Tag(name = "Auth", description = "Operações relacionadas ao usuário")
public class AuthController {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IAuthService authService ;

    @GetMapping(value = "/{id}")
    @Operation(summary = "Buscar usuário por ID")
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

    @PutMapping(value = "deactivate/{id}")
    @Operation(summary = "Desativar usuário pelo id")
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

    @PutMapping(value = "activate/{id}")
    @Operation(summary = "Ativar o usuário.")
    public ResponseEntity activate(@PathVariable Long id){
        try{
            if (id == null || id < 1) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            var service = authService.activateUser(id);

            return service.isSucesso() ? new ResponseEntity<>(service.getMensagem(),HttpStatus.OK) : new ResponseEntity<>(service.getMensagem(),HttpStatus.BAD_REQUEST);
        }
        catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/created/user")
    @Operation(summary = "Criar usuário")
    public ResponseEntity registerUser(@Valid @RequestBody CadastreRequestDTO cadastreRequestDTO) {
        try{
            if (cadastreRequestDTO == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            var service = authService.CreatedUser(cadastreRequestDTO);
            if(!service.isSucesso()){
                return new ResponseEntity<>(service,HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(service,HttpStatus.CREATED);
        }
        catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "refresh-token/")
    @Operation(summary = "Refrsh token")
    public ResponseEntity refreshToken(@Valid @RequestBody RefreshTokenRequestDTO refreshTokenRequestDTO) {
        try{
            if (refreshTokenRequestDTO.getRefreshToken() == null || refreshTokenRequestDTO.getRefreshToken().isEmpty()
            || refreshTokenRequestDTO.getUsername() == null || refreshTokenRequestDTO.getUsername().isEmpty()
            ) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            var service = authService.refreshToken(refreshTokenRequestDTO);
            Map<String, String> response = new HashMap<>();
            response.put("token", service);

            return service == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(response ,HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequest loginRequest) {
        try{
            var accessToken = authService.login(loginRequest);
            if (!accessToken.isSuccess()) {
                return new ResponseEntity<>(accessToken,HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(accessToken, HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/logout/{id}")
    public ResponseEntity logout(@RequestParam Long id) {
        try{
            var accessToken = authService.logout(id);
            if (!accessToken) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(value = "/paginator")
    @Operation(summary = "Pegar usuários paginados")
    public ResponseEntity<PaginatorGeneric> getBattalionPaginator(@RequestParam(defaultValue = "1") int page,
                                                                  @RequestParam(defaultValue = "10") int size,
                                                                  @RequestParam(required = false) String filterGeneric,
                                                                  @RequestParam(defaultValue = "true") boolean active){
        try {
            Pageable pageable  = PageRequest.of(page - 1, size);
            var service = authService.GetPaginatorUser(pageable, active, filterGeneric);

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
