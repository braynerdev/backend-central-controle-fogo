package central_controle_fogo.com.backend_central_controle_fogo.dto.auth;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserPaginatorDTO {
    private Long id;

    private boolean active;

    private String normalizedName;

    private String matriculates;

}
