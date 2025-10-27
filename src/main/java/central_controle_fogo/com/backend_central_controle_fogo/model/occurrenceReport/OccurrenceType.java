package central_controle_fogo.com.backend_central_controle_fogo.model.occurrenceReport;

import central_controle_fogo.com.backend_central_controle_fogo.model.Base;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "Occurrence_type")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OccurrenceType extends Base {
    @Column(length = 100, nullable = false)
    @NotBlank(message = "Tipo de ocorrência não pode ser nulo")
    @Size(max = 100, message = "Tipo de ocorrência pode ter no máximo 100 caracters")
    private String name;

    @OneToMany(mappedBy = "occurrenceType")
    private List<OccurrenceSubType> OccurrenceSubType;

    public OccurrenceType(String name) {

        this.name = name;
    }

}
