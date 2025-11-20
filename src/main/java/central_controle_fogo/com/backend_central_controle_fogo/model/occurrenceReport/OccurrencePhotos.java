package central_controle_fogo.com.backend_central_controle_fogo.model.occurrenceReport;

import central_controle_fogo.com.backend_central_controle_fogo.model.Base;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "occurrence_photos")
@Getter
@Setter
@NoArgsConstructor
public class OccurrencePhotos extends Base {

    @Column(nullable = false, length = 500)
    private String photoUrl;

    @ManyToOne
    @JoinColumn(name = "occurrence_id")
    private Occurrence occurrence;

    public OccurrencePhotos(Occurrence occurrence, String photoUrl) {
        this.occurrence = occurrence;
        this.photoUrl = photoUrl;
    }
}
