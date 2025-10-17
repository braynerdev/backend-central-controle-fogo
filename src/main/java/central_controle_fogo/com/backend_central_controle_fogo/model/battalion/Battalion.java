package central_controle_fogo.com.backend_central_controle_fogo.model.battalion;

import central_controle_fogo.com.backend_central_controle_fogo.model.Base;
import central_controle_fogo.com.backend_central_controle_fogo.model.auth.User;
import central_controle_fogo.com.backend_central_controle_fogo.model.generic.Address;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "battalion")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Battalion extends Base {

    @Setter
    @Column(nullable = false, length = 100)
    @NotBlank(message = "O nome do batalhão é obrigatório")
    @Size(max = 100, message = "O nome do batalhão deve ter no máximo 100 caracters")
    private String name;

    @Setter
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    private Address endereco;

    @OneToMany(mappedBy = "battalion", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<User> users;


}
