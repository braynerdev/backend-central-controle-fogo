package central_controle_fogo.com.backend_central_controle_fogo.repository.occurrenceReport;

import central_controle_fogo.com.backend_central_controle_fogo.model.occurrenceReport.Occurrence;
import central_controle_fogo.com.backend_central_controle_fogo.model.occurrenceReport.OccurrenceBattalions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OccurrenceBattalionsRepository extends JpaRepository<OccurrenceBattalions, Long> {
    List<OccurrenceBattalions> findByOccurrence(Occurrence occurrence);
}
