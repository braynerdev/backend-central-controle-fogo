package central_controle_fogo.com.backend_central_controle_fogo.model.patent;

import central_controle_fogo.com.backend_central_controle_fogo.model.Base;
import central_controle_fogo.com.backend_central_controle_fogo.model.auth.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "patent")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Patent extends Base {

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 200, nullable = true)
    private String description;

    @OneToMany(mappedBy = "patent",cascade = CascadeType.REMOVE)
    private List<User> users;
}
