package central_controle_fogo.com.backend_central_controle_fogo.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserSimpleDTO {
    private Long id;
    private String name;
    private String patentName;
    private String battalionName;
}
