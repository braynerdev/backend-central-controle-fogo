package central_controle_fogo.com.backend_central_controle_fogo.model.auth;

import central_controle_fogo.com.backend_central_controle_fogo.model.Base;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "auth_roles")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Roles extends Base {

    @Column(nullable = false, unique = true, length = 50)
    @NotBlank(message = "O nome é obrigatório")
    @Size(min = 3, max = 50, message = "O nome deve ter entre 3 e 50 caracteres")
    private String name;

    @Column(nullable = false)
    @NotBlank(message = "O nome é obrigatório")
    @Size(max = 150, message = "A descrição deve ter no máximo 150 caracteres")
    private String description;

    @OneToMany(mappedBy = "role")
    private List<UserRoles> users;

}
