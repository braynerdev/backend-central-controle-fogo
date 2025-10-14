package central_controle_fogo.com.backend_central_controle_fogo.model.auth;

import central_controle_fogo.com.backend_central_controle_fogo.Enum.PatentEnum;
import central_controle_fogo.com.backend_central_controle_fogo.model.Base;
import central_controle_fogo.com.backend_central_controle_fogo.model.battalion.Battalion;
import central_controle_fogo.com.backend_central_controle_fogo.model.generic.Address;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;

import java.util.List;
import java.time.OffsetDateTime;

@Entity
@Table(name = "auth_user")
@Getter
@NoArgsConstructor
public class User extends Base {

    @Setter
    @Column(unique = true, nullable = false, length = 30)
    private String username;

    @Column(name = "normalized_username",unique = true, nullable = false,  length = 30)
    private String normalizedUsername;

    @Column(nullable = false, length = 256)
    private String password;

    @Setter
    @Column(unique = true, nullable = false, length = 100)
    private String email;

    @Column(name = "normalized_email",unique = true, nullable = false, length = 100)
    private String normalizedEmail;

    @Setter
    @Column(name = "phone_number", nullable = false, length = 11)
    private String phoneNumber;

    @Column(unique = true, nullable = false, length = 11)
    private String cpf;

    @Column(unique = true, nullable = false, length = 30)
    private String matriculates;

    @Setter
    @Column(nullable = false, length = 200)
    private String name;

    @Column(name = "normalized_name",nullable = false, length = 200)
    private String normalizedName;

    @Setter
    @Column(name = "date_birth", nullable = false)
    private OffsetDateTime dateBirth;

    @Setter
    @Column(length = 1, nullable = false)
    private String gender;

    @Setter
    @Column(name = "refresh_token", length = 256)
    private String refreshToken;

    @Setter
    @Column(name = "refresh_token_expitarion")
    private OffsetDateTime refreshTokenExpiration;

    @Setter
    @Column(name = "using_default_password", nullable = false)
    private boolean usingDefaultPassword;

    @Setter
    @Column(name = "email_confirmed", nullable = false)
    private boolean emailConfirmed;

    @Setter
    @Column(name = "phone_number_confirmed", nullable = false)
    private boolean phoneNumberConfirmed;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<UserRoles> roles;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "battalion_id", nullable = false)
    private Battalion battalion;

    @Setter
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @Column(nullable = false, length = 19)
    private PatentEnum patent;

}
