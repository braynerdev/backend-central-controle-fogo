package central_controle_fogo.com.backend_central_controle_fogo.dto.address;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Setter;

@Data
public class AddressRegisterUserDTO {

    @Size(max = 100, message = "O logradouro aceita no máximo 100 caracteres")
    @NotBlank(message = "Logradouro é obrigatório")
    private String street;

    @NotNull(message = "O número é obrigatório")
    private int number;

    @Size(max = 100, message = "Complemento deve ter no máximo 100 caracters")
    private String complement;


    @Size(max = 100, message = "O bairro deve ter no máximo 100 caracters")
    @NotBlank(message = "O bairro é obrigatório")
    private String neighborhood;


    @Size(max = 100, message = "A cidade deve ter no máximo 100 caracters")
    @NotBlank(message = "A cidade é obrigatório")
    private String city;

    @Size(max = 100, message = "O estado deve ter no máximo 100 caracters")
    @NotBlank(message = "O estado é obrigatório")
    private String state;

    @Size(max = 8, message = "O CEP deve ter 8 caracters")
    @NotBlank(message = "O CEP é obrigatório")
    private String zipCode;
}
