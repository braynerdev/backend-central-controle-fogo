package central_controle_fogo.com.backend_central_controle_fogo.dto.occurrenceReport;

import lombok.Data;

@Data
public class OccurrenceSubTypeResponseDTO {
    private Long id;
    private String name;
    private OccurrenceTypeResponseDTO occurrenceType;
}