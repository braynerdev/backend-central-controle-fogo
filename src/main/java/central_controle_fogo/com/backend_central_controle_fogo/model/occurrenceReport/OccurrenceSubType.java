package central_controle_fogo.com.backend_central_controle_fogo.model.occurrenceReport;

import central_controle_fogo.com.backend_central_controle_fogo.model.Base;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "occurrence_sub_type")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OccurrenceSubType extends Base {
    @Column(length = 100, nullable = false)
    @NotBlank(message = "Tipo de ocorrência não pode ser nulo")
    @Size(max = 100, message = "Tipo de ocorrência pode ter no máximo 100 caracters")
    private String name;

}