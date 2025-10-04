package central_controle_fogo.com.backend_central_controle_fogo.model.auth;

import central_controle_fogo.com.backend_central_controle_fogo.model.Base;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "auth_user_roles")
@Getter
public class UserRoles extends Base {

    @ManyToOne()
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne()
    @JoinColumn(name = "role_id", nullable = false)
    private Roles role;
}
