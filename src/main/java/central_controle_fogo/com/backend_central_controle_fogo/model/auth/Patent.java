package central_controle_fogo.com.backend_central_controle_fogo.model.auth;

import central_controle_fogo.com.backend_central_controle_fogo.Enum.PatentEnum;
import central_controle_fogo.com.backend_central_controle_fogo.dto.auth.CadastreRequestDTO;
import central_controle_fogo.com.backend_central_controle_fogo.model.Base;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.boot.context.properties.bind.Name;

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
