package central_controle_fogo.com.backend_central_controle_fogo.controller;


import central_controle_fogo.com.backend_central_controle_fogo.dto.auth.CadastreRequestDTO;
import central_controle_fogo.com.backend_central_controle_fogo.dto.auth.LoginRequest;
import central_controle_fogo.com.backend_central_controle_fogo.dto.auth.LoginResponse;
import central_controle_fogo.com.backend_central_controle_fogo.dto.auth.UserResponseDTO;
import central_controle_fogo.com.backend_central_controle_fogo.service.auth.AuthService;
import central_controle_fogo.com.backend_central_controle_fogo.service.auth.IAuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Date;

@RestController
@RequestMapping("auth")
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


    @PostMapping(value = "/created/user")
    @Operation(summary = "Criar usuário")
    public ResponseEntity registerUser(@Valid @RequestBody CadastreRequestDTO cadastreRequestDTO) {
        try{
            if (cadastreRequestDTO == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            var service = authService.CreatedUser(cadastreRequestDTO);
            if(!service.isSucesso()){
                return new ResponseEntity<>(service.getMensagem(),HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(service.getMensagem(),HttpStatus.CREATED);
        }
        catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        long accessTokenTime = 300;
        long refreshTokenTime = 2592000;

        var accessToken = authService.generateToken(loginRequest, accessTokenTime);
        if (accessToken == null || accessToken.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        var dateTimeValidRefreshToken = Date.from(Instant.now().plusSeconds(refreshTokenTime));
        var refreshToken = authService.generateToken(loginRequest, refreshTokenTime);
        if (refreshToken == null || refreshToken.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        var user = authService.getByUsername(loginRequest.getUsername());
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        UserResponseDTO userResponseDTO = modelMapper.map(user, UserResponseDTO.class);
        LoginResponse responseDto = new LoginResponse(
                accessToken,
                refreshToken,
                dateTimeValidRefreshToken,
                userResponseDTO
        );

        return ResponseEntity.ok(responseDto);
    }
}
