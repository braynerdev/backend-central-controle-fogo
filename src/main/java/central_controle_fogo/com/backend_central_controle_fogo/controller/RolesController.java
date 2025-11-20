package central_controle_fogo.com.backend_central_controle_fogo.controller;

import central_controle_fogo.com.backend_central_controle_fogo.dto.roles.RoleResponseDTO;
import central_controle_fogo.com.backend_central_controle_fogo.service.roles.IRolesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/roles")
@Tag(name = "Permissões", description = "Gerenciamento de permissões de usuários")
public class RolesController {

    @Autowired
    private IRolesService rolesService;

    @GetMapping("/{id}")
    @Operation(summary = "Buscar permissão por ID")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'AGENTE', 'OBSERVADOR')")
    public ResponseEntity<?> getRoleById(@PathVariable Long id) {
        try {
            if (id == null || id < 1) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            var role = rolesService.getRoleById(id);
            if (role == null) {
                return new ResponseEntity<>("Permissão não encontrada", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(role, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    @Operation(summary = "Listar todas as permissões")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'AGENTE', 'OBSERVADOR')")
    public ResponseEntity<?> getAllRoles() {
        try {
            var roles = rolesService.getAllRoles();
            return new ResponseEntity<>(roles, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/user/{userId}/role/{roleId}")
    @Operation(summary = "Adicionar permissão a um usuário")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<?> addRoleToUser(@PathVariable Long userId, @PathVariable Long roleId) {
        try {
            if (userId == null || userId < 1 || roleId == null || roleId < 1) {
                return new ResponseEntity<>("IDs inválidos", HttpStatus.BAD_REQUEST);
            }
            var response = rolesService.addRoleToUser(userId, roleId);
            if (!response.isSucesso()) {
                return new ResponseEntity<>(response.getMensagem(), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(response.getMensagem(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/user/{userId}/role/{roleId}")
    @Operation(summary = "Remover permissão de um usuário")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<?> removeRoleFromUser(@PathVariable Long userId, @PathVariable Long roleId) {
        try {
            if (userId == null || userId < 1 || roleId == null || roleId < 1) {
                return new ResponseEntity<>("IDs inválidos", HttpStatus.BAD_REQUEST);
            }
            var response = rolesService.removeRoleFromUser(userId, roleId);
            if (!response.isSucesso()) {
                return new ResponseEntity<>(response.getMensagem(), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(response.getMensagem(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
