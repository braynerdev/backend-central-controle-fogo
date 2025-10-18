package central_controle_fogo.com.backend_central_controle_fogo.repository.battalion;

import central_controle_fogo.com.backend_central_controle_fogo.model.battalion.Battalion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBattalionRepository extends JpaRepository<Battalion,Long> {
}
