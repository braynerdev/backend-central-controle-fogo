package central_controle_fogo.com.backend_central_controle_fogo.repository.auth;

import central_controle_fogo.com.backend_central_controle_fogo.model.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRepositoryUser extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
