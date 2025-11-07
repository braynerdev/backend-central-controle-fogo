package central_controle_fogo.com.backend_central_controle_fogo.dto.occurrenceReport;

import central_controle_fogo.com.backend_central_controle_fogo.Enum.OccurrenceStatus;
import central_controle_fogo.com.backend_central_controle_fogo.dto.address.AddressResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OccurrenceResponseDTO {
    private Long id;
    private boolean occurrenceHasVictims;
    private String occurrenceRequester;
    private String occurrenceRequesterPhoneNumber;
    private OccurrenceSubtypeDTO occurrenceSubType;

    private String occurrenceDetails;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private LocalDateTime occurrenceArrivalTime;
    private OccurrenceStatus status;
    private OffsetDateTime createDate;
    private boolean active;
    
    private AddressResponseDTO address;
}
