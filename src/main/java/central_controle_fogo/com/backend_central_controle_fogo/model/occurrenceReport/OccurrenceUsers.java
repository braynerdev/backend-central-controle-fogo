package central_controle_fogo.com.backend_central_controle_fogo.model.occurrenceReport;

import central_controle_fogo.com.backend_central_controle_fogo.model.Base;
import central_controle_fogo.com.backend_central_controle_fogo.model.auth.User;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "occurence_user")
@Getter
@Setter
@NoArgsConstructor

public class OccurrenceUsers extends Base {

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "occurence_id", nullable = false)
    private Occurrence occurrence;

    public OccurrenceUsers(Occurrence occurrence, User user) {
        this.occurrence = occurrence;
        this.user = user;
    }
}
