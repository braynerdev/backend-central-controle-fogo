package central_controle_fogo.com.backend_central_controle_fogo.model.occurrenceReport;


import central_controle_fogo.com.backend_central_controle_fogo.model.Base;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "occurrence_nature")
@Getter
@Setter
@NoArgsConstructor
public class OccurrenceNature extends Base {
    @Column(length = 100, nullable = false)
    private String name;
}
