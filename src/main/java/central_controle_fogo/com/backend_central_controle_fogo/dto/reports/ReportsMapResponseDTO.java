package central_controle_fogo.com.backend_central_controle_fogo.dto.reports;

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
public class ReportsMapResponseDTO {
    private Long id;
    private String nature;
    private String type;
    private String subType;
    private String description;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private OffsetDateTime data;
}
