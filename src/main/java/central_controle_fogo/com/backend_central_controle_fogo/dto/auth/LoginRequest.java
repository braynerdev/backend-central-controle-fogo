package central_controle_fogo.com.backend_central_controle_fogo.dto.auth;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"username", "password"})
public class LoginRequest {

    private String username;
    private String password;
}
