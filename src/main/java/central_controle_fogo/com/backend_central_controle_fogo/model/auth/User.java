package central_controle_fogo.com.backend_central_controle_fogo.model.auth;

import central_controle_fogo.com.backend_central_controle_fogo.model.Base;
import central_controle_fogo.com.backend_central_controle_fogo.model.battalion.Battalion;
import central_controle_fogo.com.backend_central_controle_fogo.model.generic.Address;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;
import java.util.List;
import java.util.Locale;

@Entity
@Table(name = "auth_user")
@Getter
@NoArgsConstructor
public class User extends Base {

    @Setter
    @Column(unique = true, nullable = false, length = 30)
    @NotBlank(message = "O username é obrigatório")
    @Size(min = 5, max = 30, message = "O username deve ter entre 5 e 30 caracteres")
    private String username;

    @Column(name = "normalized_username",unique = true, nullable = false,  length = 30)
    private String normalizedUsername;

    @Column(nullable = false, length = 256)
    @NotBlank(message = "A senha é obrigatório")
    private String password;

    @Setter
    @Column(unique = true, nullable = false, length = 100)
    @NotBlank(message = "O e-mail é obrigatório")
    @Size(max = 100, message = "O e-mail deve ter no máximo 100 caracteres")
    private String email;

    @Column(name = "normalized_email",unique = true, nullable = false, length = 100)
    private String normalizedEmail;

    @Setter
    @Column(name = "phone_number", nullable = false, length = 11)
    @NotBlank(message = "O número de telefone é obrigatório")
    @Size(min= 11, max = 11, message = "O número de telefone deve ter 11 caracteres")
    private String phoneNumber;

    @Column(unique = true, nullable = false, length = 11)
    @NotBlank(message = "O CPF é obrigatório")
    @Size(min = 11, max = 11, message = "O CPF deve ter no máximo 11 caracteres")
    private String cpf;

    @Setter
    @Column(nullable = false, length = 200)
    @NotBlank(message = "O nome é obrigatório")
    @Size(max = 200, message = "O nome deve ter no máximo 200 caracteres")
    private String name;

    @Column(name = "normalized_name",nullable = false, length = 200)
    private String normalizedName;

    @Setter
    @Column(name = "date_birth", nullable = false)
    @NotBlank(message = "Data de nascimento é obrigatório")
    private Date dateBirth;

    @Setter
    @Column(length = 1, nullable = false)
    @NotBlank(message = "O gênero é obrigatório")
    @Size(max = 1, message = "O gênero deve no maximo 1 caractere")
    private String gender;

    @Setter
    @Column(name = "refresh_token", length = 256)
    private String refreshToken;

    @Setter
    @Column(name = "refresh_token_expitarion")
    private Date refreshTokenExpiration;

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

    @ManyToOne()
    @JoinColumn(name = "batalhao_id", nullable = false)
    private Battalion battalion;

    @Setter
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    private Address endereco;

}
