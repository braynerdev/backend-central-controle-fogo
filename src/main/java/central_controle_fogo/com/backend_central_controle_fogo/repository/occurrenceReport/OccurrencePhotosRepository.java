package central_controle_fogo.com.backend_central_controle_fogo.repository.occurrenceReport;

import central_controle_fogo.com.backend_central_controle_fogo.model.occurrenceReport.Occurrence;
import central_controle_fogo.com.backend_central_controle_fogo.model.occurrenceReport.OccurrencePhotos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OccurrencePhotosRepository extends JpaRepository<OccurrencePhotos, Long> {
    List<OccurrencePhotos> findByOccurrence(Occurrence occurrence);
}
