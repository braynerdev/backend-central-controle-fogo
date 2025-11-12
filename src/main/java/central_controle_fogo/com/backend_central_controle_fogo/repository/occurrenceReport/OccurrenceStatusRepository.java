package central_controle_fogo.com.backend_central_controle_fogo.repository.occurrenceReport;

import central_controle_fogo.com.backend_central_controle_fogo.model.occurrenceReport.OccurrenceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OccurrenceStatusRepository extends JpaRepository<OccurrenceStatus, Long> {
    public OccurrenceStatus findByname(String name);
}
