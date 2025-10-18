package central_controle_fogo.com.backend_central_controle_fogo.dto.battalion;

import central_controle_fogo.com.backend_central_controle_fogo.dto.address.AddressRegisterDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BattalionRequestDTO {

    @NotBlank(message = "O nome do batalhão é obrigatório")
    @Size(max = 100, message = "O nome do batalhão deve ter no máximo 100 caracteres")
    private String name;

    @NotBlank(message = "O e-mail é obrigatório")
    @Size(max = 100, message = "O e-mail deve ter no máximo 100 caracteres")
    private String phoneNumber;

    @NotBlank(message = "O número de telefone é obrigatório")
    @Size(min = 10, max = 11, message = "o número de telefone deve ter de 10 a 11 caracteres")
    private String email;

    @Valid
    private AddressRegisterDTO address;

}
