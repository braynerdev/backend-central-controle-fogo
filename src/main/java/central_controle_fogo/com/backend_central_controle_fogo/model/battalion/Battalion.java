package central_controle_fogo.com.backend_central_controle_fogo.model.battalion;

import central_controle_fogo.com.backend_central_controle_fogo.model.Base;
import central_controle_fogo.com.backend_central_controle_fogo.model.auth.User;
import central_controle_fogo.com.backend_central_controle_fogo.model.generic.Address;
import central_controle_fogo.com.backend_central_controle_fogo.model.vehicles.Vehicle;
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
    private String name;

    @Setter
    @Column(name = "phone_number", nullable = false, length = 11)
    private String phoneNumber;

    @Setter
    @Column(unique = true ,nullable = false, length = 100 )
    private String email;

    @Setter
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    private Address endereco;

    @OneToMany(mappedBy = "battalion", cascade = CascadeType.REMOVE)
    private List<User> users;

    @OneToMany(mappedBy = "battalion", cascade = CascadeType.REMOVE)
    private List<Vehicle> battalions;

    public Battalion(String name, String phoneNumber,  String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

}
