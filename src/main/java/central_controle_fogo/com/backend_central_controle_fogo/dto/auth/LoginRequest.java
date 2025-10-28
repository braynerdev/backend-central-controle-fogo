package central_controle_fogo.com.backend_central_controle_fogo.dto.auth;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"email", "password"})
public class LoginRequest {

    @Email
    @NotBlank(message = "Email não pode ser nulo!")
    @Size(max = 100, message = "O email deve ter no maximo 100 caracteres!")
    private String email;

    @NotBlank(message = "A senha é obrigatório")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&._-])[A-Za-z\\d@$!%*?&._-]{8,}$",
            message = "Senha enviada no formato errado!"
    )
    private String password;
}
