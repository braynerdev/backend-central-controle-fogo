package central_controle_fogo.com.backend_central_controle_fogo.dto.occurrenceReport.occurrenceFirst;


import central_controle_fogo.com.backend_central_controle_fogo.dto.address.AddressRegisterDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OccurrenceFirstRequestDTO {
    @NotNull(message = "Selecione se há vítimas")
    private boolean occurrenceHasVictims;

    @NotBlank(message = "Insira o nome do solicitante")
    private String occurrenceRequester;

    @NotBlank(message = "Insira o telefone para contato do solicitante")
    @Size(min = 11, max = 11, message = "O número de telefone deve conter 11 dígitos")
    private String occurrenceRequesterPhoneNumber;

    @NotNull(message = "Insira o subtipo")
    private Long occurrenceSubType;

    @NotNull(message = "O endereço é obrigatório")
    @Valid
    private AddressRegisterDTO address;

    private Long status;
}
