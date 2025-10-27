package central_controle_fogo.com.backend_central_controle_fogo.dto.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RefreshTokenRequestDTO {
    @NotBlank(message = "Refresh Token nao pode ser nulo!")
    private String refreshToken;

    @NotBlank(message = "Username não pode ser nulo!")
    private String username;
}
