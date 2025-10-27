package central_controle_fogo.com.backend_central_controle_fogo.dto.patent;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PatentResponseDTO {

    private Long id;

    private boolean active;

    private String name;

}
