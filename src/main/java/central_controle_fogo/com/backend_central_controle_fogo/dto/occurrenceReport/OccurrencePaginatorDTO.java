package central_controle_fogo.com.backend_central_controle_fogo.dto.occurrenceReport;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OccurrencePaginatorDTO {
    private Long id;
    private boolean occurrenceHasVictims;
    private boolean active;
    private String occurrenceRequester;
    private String occurrenceRequesterPhoneNumber;
    private String occurrenceNature;
    private String occurrenceType;
    private String occurrenceSubType;
    private String status;
    private OffsetDateTime createDate;
    private List<String> battalions;
    private Integer photoCount;
}
