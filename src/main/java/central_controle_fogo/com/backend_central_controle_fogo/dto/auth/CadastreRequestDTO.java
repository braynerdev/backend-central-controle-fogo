package central_controle_fogo.com.backend_central_controle_fogo.dto.auth;

import central_controle_fogo.com.backend_central_controle_fogo.Enum.PatentEnum;
import central_controle_fogo.com.backend_central_controle_fogo.model.battalion.Battalion;
import central_controle_fogo.com.backend_central_controle_fogo.model.generic.Address;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;


import java.time.OffsetDateTime;
import java.util.List;
@Data
@AllArgsConstructor
public class CadastreRequestDTO {

    @NotBlank(message = "O username é obrigatório")
    @Size(min = 5, max = 30, message = "O username deve ter entre 5 e 30 caracteres")
    private String username;

    @Column(nullable = false, length = 256)
    @NotBlank(message = "A senha é obrigatório")
    private String password;

    @NotBlank(message = "O e-mail é obrigatório")
    @Size(max = 100, message = "O e-mail deve ter no máximo 100 caracteres")
    private String email;


    @NotBlank(message = "O número de telefone é obrigatório")
    @Size(min= 11, max = 11, message = "O número de telefone deve ter 11 caracteres")
    private String phoneNumber;

    @NotBlank(message = "O CPF é obrigatório")
    @Size(min = 11, max = 11, message = "O CPF deve ter no máximo 11 caracteres")
    private String cpf;

    @NotBlank(message = "A matrícula é obrigatória")
    @Size(max = 30, message = "A matrícula deve ter no máximo 30 caracteres")
    private String matriculates;

    @NotBlank(message = "O nome é obrigatório")
    @Size(max = 200, message = "O nome deve ter no máximo 200 caracteres")
    private String name;

    @NotNull(message = "Data de nascimento é obrigatório")
    private OffsetDateTime dateBirth;

    @NotBlank(message = "O gênero é obrigatório")
    @Size(max = 1, message = "O gênero deve no maximo 1 caractere")
    private String gender;

    @NotNull(message = "O batalhão é obrigatório")
    private Battalion battalion;

    @NotNull(message = "O endereço é obrigatório")
    private Address address;

    @NotNull(message = "A patente é obrigatória")
    @Size(max = 19, message = "A patente deve ter no máximo 19 caracteres")
    private PatentEnum patent;


}
