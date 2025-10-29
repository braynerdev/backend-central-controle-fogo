package central_controle_fogo.com.backend_central_controle_fogo.dto.auth;

import central_controle_fogo.com.backend_central_controle_fogo.dto.patent.PatentResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatentResponseAllDTO {
    List<PatentResponseDTO> patentResponseDTOList;
}
