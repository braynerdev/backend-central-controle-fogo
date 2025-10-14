package central_controle_fogo.com.backend_central_controle_fogo.repository.auth;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IRepositoryJPA<T> extends JpaRepository<T, Long> {

}
