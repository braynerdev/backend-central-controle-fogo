package central_controle_fogo.com.backend_central_controle_fogo.dto.occurrenceReport.occurrenceSecond;

//public Occurrence(String occurrenceDetails, BigDecimal latitude, BigDecimal longitude, LocalDateTime occurrenceArrivalTime, List<OccurenceUsers> users, OccurrenceStatus status) {
//    this.occurrenceDetails = occurrenceDetails;
//    this.latitude = latitude;
//    this.longitude = longitude;
//    this.occurrenceArrivalTime = occurrenceArrivalTime;
//    this.users = users;
//    this.status = status;
//}

import central_controle_fogo.com.backend_central_controle_fogo.model.occurrenceReport.OccurrenceVehicles;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OccurrenceSecondRequestDTO {
    @NotBlank(message = "Detalhe da ocorrência não pode ser nulo")
    @Size(max = 500, message = "Detalhe da ocorrência pode ter no máximo 500 caracters")
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
    private OffsetDateTime occurrenceArrivalTime;

    @NotNull(message = "A lista de usuários é obrigatória")
    private List<Long> userIds;

    @NotNull(message = "A lista de veículos é obrigatória")
    private List<Long> Vehicles;

    @NotNull(message = "O status não pode ser nulo")
    private Long status;

    @NotNull(message = "O ID da ocorrência é obrigatório")
    private Long occurrenceId;
}
