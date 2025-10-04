package central_controle_fogo.com.backend_central_controle_fogo.model.auth;

import central_controle_fogo.com.backend_central_controle_fogo.model.Base;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "auth_roles")
@Getter
public class Roles extends Base {

    @Column(nullable = false, unique = true)
    @NotBlank(message = "O nome é obrigatório")
    private String name;
}
