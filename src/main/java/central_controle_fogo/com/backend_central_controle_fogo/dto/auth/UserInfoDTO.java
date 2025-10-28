package central_controle_fogo.com.backend_central_controle_fogo.dto.auth;

import central_controle_fogo.com.backend_central_controle_fogo.dto.patent.PatentResponseDTO;
import central_controle_fogo.com.backend_central_controle_fogo.model.auth.UserRoles;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoDTO {
    private Long id;

    private OffsetDateTime createdAt;

    private OffsetDateTime updatedAt;

    private boolean active;

    private String username;

    private String email;

    private String cpf;

    private String phoneNumber;

    private String matriculates;

    private String normalizedName;

    private String gender;

    private boolean usingDefaultPassword;

    private boolean emailConfirmed;

    private boolean phoneNumberConfirmed;

    private PatentResponseDTO patent;

    private List<UserRolesDTO> userRoles;
}
