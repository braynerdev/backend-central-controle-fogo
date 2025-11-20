package central_controle_fogo.com.backend_central_controle_fogo.service.roles;

import central_controle_fogo.com.backend_central_controle_fogo.dto.generic.ResponseDTO;
import central_controle_fogo.com.backend_central_controle_fogo.dto.roles.RoleResponseDTO;

import java.util.List;

public interface IRolesService {
    
    RoleResponseDTO getRoleById(Long id);
    
    List<RoleResponseDTO> getAllRoles();
    
    ResponseDTO addRoleToUser(Long userId, Long roleId);
    
    ResponseDTO removeRoleFromUser(Long userId, Long roleId);
}
