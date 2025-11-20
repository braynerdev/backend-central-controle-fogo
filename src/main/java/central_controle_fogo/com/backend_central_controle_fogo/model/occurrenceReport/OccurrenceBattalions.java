package central_controle_fogo.com.backend_central_controle_fogo.model.occurrenceReport;

import central_controle_fogo.com.backend_central_controle_fogo.model.Base;
import central_controle_fogo.com.backend_central_controle_fogo.model.battalion.Battalion;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "occurrence_battalions")
@Getter
@Setter
@NoArgsConstructor
public class OccurrenceBattalions extends Base {

    @ManyToOne
    @JoinColumn(name = "battalion_id")
    private Battalion battalion;

    @ManyToOne
    @JoinColumn(name = "occurrence_id")
    private Occurrence occurrence;

    public OccurrenceBattalions(Occurrence occurrence, Battalion battalion) {
        this.occurrence = occurrence;
        this.battalion = battalion;
    }
}
