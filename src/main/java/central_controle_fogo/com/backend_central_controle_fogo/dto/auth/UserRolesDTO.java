package central_controle_fogo.com.backend_central_controle_fogo.dto.auth;

import central_controle_fogo.com.backend_central_controle_fogo.Enum.RolesEnum;
import central_controle_fogo.com.backend_central_controle_fogo.model.auth.Roles;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRolesDTO {
    private RolesDTO role;
}
