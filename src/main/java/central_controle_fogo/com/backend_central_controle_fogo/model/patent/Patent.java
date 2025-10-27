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

    @Column(length = 100, nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "patent",cascade = CascadeType.REMOVE)
    private List<User> users;
}
