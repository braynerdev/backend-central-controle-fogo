package central_controle_fogo.com.backend_central_controle_fogo.dto.auth;

import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
public class LoginResponse {
    private boolean success;
    private String message;
    private String accessToken;
    private String refreshToken;
    private OffsetDateTime expiresRefreshToken;
    private UserResponseDTO user;

    public LoginResponse(boolean success, String message) {
        this.message = message;
        this.success = success;
    }

    public LoginResponse(boolean success, String message, String accessToken, String refreshToken,OffsetDateTime expiresRefreshToken, UserResponseDTO user) {
        this.success = success;
        this.user = user;
        this.expiresRefreshToken = expiresRefreshToken;
        this.refreshToken = refreshToken;
        this.accessToken = accessToken;
        this.message = message;
    }
}
