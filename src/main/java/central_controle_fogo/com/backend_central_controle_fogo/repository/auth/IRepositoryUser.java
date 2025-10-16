package central_controle_fogo.com.backend_central_controle_fogo.repository.auth;

import central_controle_fogo.com.backend_central_controle_fogo.model.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRepositoryUser extends JpaRepository<User, Long> {
}
