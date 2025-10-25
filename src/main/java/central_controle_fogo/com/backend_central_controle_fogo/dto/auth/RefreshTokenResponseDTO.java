package central_controle_fogo.com.backend_central_controle_fogo.dto.auth;

import lombok.*;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RefreshTokenResponseDTO {
    private String token;
}
