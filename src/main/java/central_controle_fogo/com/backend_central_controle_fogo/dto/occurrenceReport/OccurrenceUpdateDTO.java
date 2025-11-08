package central_controle_fogo.com.backend_central_controle_fogo.dto.occurrenceReport;

import central_controle_fogo.com.backend_central_controle_fogo.dto.address.AddressRegisterDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OccurrenceUpdateDTO {

    @NotNull(message = "Selecione se há vítimas")
    private boolean occurrenceHasVictims;

    @NotBlank(message = "Insira o nome do solicitante")
    private String occurrenceRequester;

    @NotBlank(message = "Insira o telefone para contato do solicitante")
    private String occurrenceRequesterPhoneNumber;

    @NotNull(message = "Insira o subtipo da ocorrência")
    private Long occurrenceSubType;

    @NotNull(message = "O endereço é obrigatório")
    @Valid
    private AddressRegisterDTO address;

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
    private OffsetDateTime occurrenceArrivalTime;

    @NotNull(message = "A lista de usuários é obrigatória")
    private List<Long> userIds;

    @NotNull(message = "A lista de veículos é obrigatória")
    private List<Long> vehicles;

    @NotNull(message = "O status é obrigatório")
    private Long status;
}
