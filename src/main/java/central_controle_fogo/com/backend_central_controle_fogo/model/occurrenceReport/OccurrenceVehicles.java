package central_controle_fogo.com.backend_central_controle_fogo.model.occurrenceReport;

import central_controle_fogo.com.backend_central_controle_fogo.model.Base;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "occurence_vehicles")
@Getter
@Setter
@NoArgsConstructor

public class OccurrenceVehicles extends Base {

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Occurrence vehicle;

    @ManyToOne
    @JoinColumn(name = "occurrence_id")
    private Occurrence occurrence;

    public OccurrenceVehicles(Occurrence occurrence, Occurrence vehicle) {
        this.occurrence = occurrence;
        this.vehicle = vehicle;
    }
}
