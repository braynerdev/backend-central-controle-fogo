package central_controle_fogo.com.backend_central_controle_fogo.repository.patent;

import central_controle_fogo.com.backend_central_controle_fogo.model.auth.Patent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPatent extends JpaRepository<Patent,Long> {
}
