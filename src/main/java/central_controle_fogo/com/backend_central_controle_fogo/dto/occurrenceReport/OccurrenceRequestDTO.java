package central_controle_fogo.com.backend_central_controle_fogo.dto.occurrenceReport;

import central_controle_fogo.com.backend_central_controle_fogo.model.generic.Address;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;


public class OccurrenceRequestDTO {

    @NotNull(message = "Selecione se há vítimas")
    private Boolean occurrenceHasVictims;

    @NotNull(message = "Selecione se há prioridade no atendimento")
    private Boolean occurrenceIsPriority;

    @NotBlank(message = "Insira o nome do solicitante")
    @Size(max = 50)
    private String occurrenceRequester;

    @NotBlank(message = "Insira o telefone para contato do solicitante")
    @Size(min = 10, max = 11, message = "Telefone deve ter 10 ou 11 dígitos")
    private String occurrenceRequesterPhoneNumber;

    @NotNull(message = "O ID do subtipo da ocorrência é obrigatório")
    private Long occurrenceSubTypeId; // Apenas o ID é enviado

    @NotNull(message = "O endereço é obrigatório")
    @Valid // Valida o objeto Address aninhado
    private Address address;

    @NotBlank(message = "Descreva detalhes da ocorrência")
    @Size(max = 2000)
    private String occurrenceDetails;


    private BigDecimal latitude;

    private BigDecimal longitude;
}