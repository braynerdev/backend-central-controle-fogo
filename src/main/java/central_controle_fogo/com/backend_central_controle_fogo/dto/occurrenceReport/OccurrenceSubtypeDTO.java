package central_controle_fogo.com.backend_central_controle_fogo.dto.occurrenceReport;

import central_controle_fogo.com.backend_central_controle_fogo.model.occurrenceReport.OccurrenceTypeDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OccurrenceSubtypeDTO {

    private Long id;
    private String name;
    private OccurrenceTypeDTO occurrenceType;

}
