package central_controle_fogo.com.backend_central_controle_fogo.dto.occurrenceReport;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OccurrenceOnSiteDTO {
    
    @NotBlank(message = "Insira os detalhes da ocorrência")
    private String occurrenceDetails;

    @NotNull(message = "A latitude é obrigatória")
    @DecimalMin(value = "-90.0", message = "A latitude deve estar entre -90 e 90")
    @DecimalMax(value = "90.0", message = "A latitude deve estar entre -90 e 90")
    private BigDecimal latitude;

    @NotNull(message = "A longitude é obrigatória")
    @DecimalMin(value = "-180.0", message = "A longitude deve estar entre -180 e 180")
    @DecimalMax(value = "180.0", message = "A longitude deve estar entre -180 e 180")
    private BigDecimal longitude;

    @NotNull(message = "O horário de chegada é obrigatório")
    private LocalDateTime occurrenceArrivalTime;

    @NotNull(message = "A lista de usuários é obrigatória")
    private List<Long> userIds;
}
