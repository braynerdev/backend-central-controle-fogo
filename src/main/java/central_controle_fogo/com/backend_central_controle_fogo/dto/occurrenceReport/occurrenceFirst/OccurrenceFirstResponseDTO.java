package central_controle_fogo.com.backend_central_controle_fogo.dto.occurrenceReport.occurrenceFirst;

import central_controle_fogo.com.backend_central_controle_fogo.model.occurrenceReport.OccurrenceStatus;
import central_controle_fogo.com.backend_central_controle_fogo.dto.address.AddressRegisterDTO;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OccurrenceFirstResponseDTO {
    private Long id;

    private boolean occurrenceHasVictims;

    private String occurrenceRequester;

    private String occurrenceRequesterPhoneNumber;

    private String occurrenceNature;

    private String occurrenceType;

    private String occurrenceSubType;

    private AddressRegisterDTO address;

    private String status;
}
