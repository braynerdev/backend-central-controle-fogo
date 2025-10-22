package central_controle_fogo.com.backend_central_controle_fogo.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
    private Long id;

    private String username;

    private String cpf;

    private String matriculates;

    private String name;

    private String gender;

}
