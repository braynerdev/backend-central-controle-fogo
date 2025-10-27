package central_controle_fogo.com.backend_central_controle_fogo.repository.patent;

import central_controle_fogo.com.backend_central_controle_fogo.model.patent.Patent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPatentRepository extends JpaRepository<Patent,Long> {
    Optional<Patent> findByName(String name);
}
