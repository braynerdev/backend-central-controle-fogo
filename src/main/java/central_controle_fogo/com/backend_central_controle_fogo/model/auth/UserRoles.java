package central_controle_fogo.com.backend_central_controle_fogo.model.auth;

import central_controle_fogo.com.backend_central_controle_fogo.model.Base;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "auth_user_roles")
@Getter
@NoArgsConstructor
public class UserRoles extends Base {

    @ManyToOne()
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne()
    @JoinColumn(name = "role_id", nullable = false)
    private Roles role;

    public UserRoles(User user, Roles role) {
        this.user = user;
        this.role = role;
    }
}
