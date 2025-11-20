package central_controle_fogo.com.backend_central_controle_fogo.service.roles;

import central_controle_fogo.com.backend_central_controle_fogo.dto.generic.ResponseDTO;
import central_controle_fogo.com.backend_central_controle_fogo.dto.roles.RoleResponseDTO;
import central_controle_fogo.com.backend_central_controle_fogo.model.auth.UserRoles;
import central_controle_fogo.com.backend_central_controle_fogo.repository.auth.IRolesRepository;
import central_controle_fogo.com.backend_central_controle_fogo.repository.auth.IRepositoryUser;
import central_controle_fogo.com.backend_central_controle_fogo.repository.auth.IUserRolesRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RolesService implements IRolesService {

    @Autowired
    private IRolesRepository rolesRepository;

    @Autowired
    private IRepositoryUser userRepository;

    @Autowired
    private IUserRolesRepository userRolesRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public RoleResponseDTO getRoleById(Long id) {
        try {
            var role = rolesRepository.findById(id).orElse(null);
            if (role == null) {
                return null;
            }
            return modelMapper.map(role, RoleResponseDTO.class);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<RoleResponseDTO> getAllRoles() {
        try {
            return rolesRepository.findAll().stream()
                    .map(role -> modelMapper.map(role, RoleResponseDTO.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            return List.of();
        }
    }

    @Override
    @Transactional
    public ResponseDTO addRoleToUser(Long userId, Long roleId) {
        try {
            var user = userRepository.findById(userId).orElse(null);
            if (user == null) {
                return ResponseDTO.erro("Usuário não encontrado");
            }

            var role = rolesRepository.findById(roleId).orElse(null);
            if (role == null) {
                return ResponseDTO.erro("Permissão não encontrada");
            }

            var existingUserRole = userRolesRepository.findByUserAndRole(user, role);
            if (existingUserRole.isPresent()) {
                return ResponseDTO.erro("Usuário já possui essa permissão");
            }

            var userRole = new UserRoles(user, role);
            userRolesRepository.save(userRole);

            return ResponseDTO.sucesso("Permissão adicionada ao usuário com sucesso");
        } catch (Exception e) {
            return ResponseDTO.erro("Erro ao adicionar permissão ao usuário");
        }
    }

    @Override
    @Transactional
    public ResponseDTO removeRoleFromUser(Long userId, Long roleId) {
        try {
            var user = userRepository.findById(userId).orElse(null);
            if (user == null) {
                return ResponseDTO.erro("Usuário não encontrado");
            }

            var role = rolesRepository.findById(roleId).orElse(null);
            if (role == null) {
                return ResponseDTO.erro("Permissão não encontrada");
            }

            var userRole = userRolesRepository.findByUserAndRole(user, role);
            if (userRole.isEmpty()) {
                return ResponseDTO.erro("Usuário não possui essa permissão");
            }

            userRolesRepository.delete(userRole.get());

            return ResponseDTO.sucesso("Permissão removida do usuário com sucesso");
        } catch (Exception e) {
            return ResponseDTO.erro("Erro ao remover permissão do usuário");
        }
    }
}
