package central_controle_fogo.com.backend_central_controle_fogo.dto.auth;

import lombok.Data;

@Data
public class LoginRequest {

    private String Username;
    private String Password;
}
