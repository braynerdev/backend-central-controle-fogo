package central_controle_fogo.com.backend_central_controle_fogo.repository.occurrenceReport;

import central_controle_fogo.com.backend_central_controle_fogo.model.occurrenceReport.OccurrenceSubType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OccurrenceSubTypeRepository extends JpaRepository<OccurrenceSubType, Long> {
}