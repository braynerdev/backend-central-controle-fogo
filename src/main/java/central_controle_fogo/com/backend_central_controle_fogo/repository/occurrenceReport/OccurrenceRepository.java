package central_controle_fogo.com.backend_central_controle_fogo.repository.occurrenceReport;

import central_controle_fogo.com.backend_central_controle_fogo.model.occurrenceReport.Occurrence;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OccurrenceRepository extends JpaRepository<Occurrence, Long> {
    Page<Occurrence> findByActive(boolean active, Pageable pageable);
    Page<Occurrence> findByActiveAndOccurrenceSubTypeNameContainingIgnoreCase(boolean active, String occurrenceSubTypeName, Pageable pageable);
}
