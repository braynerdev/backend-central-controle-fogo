package central_controle_fogo.com.backend_central_controle_fogo.dto.occurrenceReport;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OccurrenceInfoMapDTO {
    private Long id;
    private Long typeId;
    private String typeName;
    private Long subtypeId;
    private String subtypeName;
    private Long natureId;
    private String natureName;
    private Long statusId;
    private String statusName;
    private String description;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private OffsetDateTime date;
}
