package central_controle_fogo.com.backend_central_controle_fogo.model.auth;

import central_controle_fogo.com.backend_central_controle_fogo.model.Base;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;

@Entity
@Table(name = "auth_user")
@Getter
@AllArgsConstructor
public class User extends Base {

    @Column(unique = true, nullable = false, length = 30)
    @NotBlank(message = "O username é obrigatório")
    @Size(min = 5, max = 30, message = "O username deve ter entre 5 e 30 caracteres")
    private String username;

    @Column(name = "normalized_name",unique = true, nullable = false,  length = 30)
    private String normalizedUsername;

    @Column(nullable = false, length = 256)
    @NotBlank(message = "A senha é obrigatório")
    private String password;

    @Column(unique = true, nullable = false, length = 100)
    @NotBlank(message = "O e-mail é obrigatório")
    @Size(max = 100, message = "O e-mail deve ter no máximo 100 caracteres")
    private String email;

    @Column(name = "normalized_email",unique = true, nullable = false, length = 100)
    private String normalizedEmail;

    @Column(name = "phone_number", nullable = false)
    @NotBlank(message = "O número de telefone é obrigatório")
    private String phoneNumber;

    @Column(nullable = false, length = 200)
    @NotBlank(message = "O nome é obrigatório")
    private String name;

    @Column(name = "normalized_name",nullable = false, length = 200)
    private String normalizedName;

    @Setter
    @Column(name = "refresh_token", length = 256)
    private String refreshToken;

    @Setter
    @Column(name = "refresh_token_expitarion", length = 256)
    private Date refreshTokenExpiration;

    @Setter
    @Column(name = "using_default_password", nullable = false)
    private boolean usingDefaultPassword = true;

    @Setter
    @Column(name = "email_confirmed", nullable = false)
    private boolean emailConfirmed = false;

    @Setter
    @Column(name = "phone_number_confirmed", nullable = false)
    private boolean phoneNumberConfirmed = false;

}
