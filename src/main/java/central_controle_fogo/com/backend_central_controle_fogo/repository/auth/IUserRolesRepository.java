package central_controle_fogo.com.backend_central_controle_fogo.repository.auth;

import central_controle_fogo.com.backend_central_controle_fogo.model.auth.Roles;
import central_controle_fogo.com.backend_central_controle_fogo.model.auth.User;
import central_controle_fogo.com.backend_central_controle_fogo.model.auth.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserRolesRepository extends JpaRepository<UserRoles, Long> {
    
    Optional<UserRoles> findByUserAndRole(User user, Roles role);
    
    List<UserRoles> findByUser(User user);
    
    void deleteByUserAndRole(User user, Roles role);
}
