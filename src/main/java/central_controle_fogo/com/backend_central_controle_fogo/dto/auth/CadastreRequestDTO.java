package central_controle_fogo.com.backend_central_controle_fogo.dto.auth;

import central_controle_fogo.com.backend_central_controle_fogo.dto.address.AddressRegisterDTO;
import central_controle_fogo.com.backend_central_controle_fogo.model.auth.User;
import central_controle_fogo.com.backend_central_controle_fogo.repository.auth.IRepositoryUser;
import central_controle_fogo.com.backend_central_controle_fogo.repository.battalion.IBattalionRepository;
import central_controle_fogo.com.backend_central_controle_fogo.validation.annotation.ExistsInDatabase;
import central_controle_fogo.com.backend_central_controle_fogo.validation.annotation.Unique;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class CadastreRequestDTO {

    @NotBlank(message = "O username é obrigatório")
    @Size(min = 5, max = 30, message = "O username deve ter entre 5 e 30 caracteres")
    @Unique(repository = IRepositoryUser.class ,field = "username", message = "Já existe um usuário com esse username")
    private String username;

    @Column(nullable = false, length = 256)
    @NotBlank(message = "A senha é obrigatório")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&._-])[A-Za-z\\d@$!%*?&._-]{8,}$",
            message = "A senha deve ter pelo menos 8 caracteres, incluindo uma letra maiúscula, " +
                    "uma minúscula, um número e um caractere especial."
    )
    private String password;

    @NotBlank(message = "O e-mail é obrigatório")
    @Size(max = 100, message = "O e-mail deve ter no máximo 100 caracteres")
    @Email()
    @Unique(repository = IRepositoryUser.class ,field = "email", message = "Já existe um usuário com esse e-mail")
        private String email;


    @NotBlank(message = "O número de telefone é obrigatório")
    @Size(min= 11, max = 11, message = "O número de telefone deve ter 11 caracteres")
    @Unique(repository = IRepositoryUser.class ,field = "phoneNumber", message = "Já existe um usuário com esse número de telefone")
    private String phoneNumber;

    @NotBlank(message = "O CPF é obrigatório")
    @Size(min = 11, max = 11, message = "O CPF deve ter no máximo 11 caracteres")
    @Unique(repository = IRepositoryUser.class ,field = "username", message = "Já existe um usuário com esse CPF")
    private String cpf;

    @NotBlank(message = "A matrícula é obrigatória")
    @Size(max = 30, message = "A matrícula deve ter no máximo 30 caracteres")
    @Unique(repository = IRepositoryUser.class ,field = "matriculates", message = "Já existe um usuário com essa matrícula")
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
    @ExistsInDatabase(repository = IBattalionRepository.class, message = "Batalhão não encontrado")
    private Long battalion;

    @NotNull(message = "O endereço é obrigatório")
    private AddressRegisterDTO address;

    @NotNull(message = "A patente é obrigatória")
//    @ExistsInDatabase(repository = .class, message = "Patente não encontrado")
    private Long patent;

    private List<Long> roleIds;


    public static User mapDto(CadastreRequestDTO dto) {
        return new User(
                dto.getUsername(),
                dto.getEmail(),
                dto.getPhoneNumber(),
                dto.getCpf(),
                dto.getMatriculates(),
                dto.getName(),
                dto.getDateBirth(),
                dto.getGender()
        );
    }
}
