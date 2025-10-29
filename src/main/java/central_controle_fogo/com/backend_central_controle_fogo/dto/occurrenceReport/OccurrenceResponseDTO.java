package central_controle_fogo.com.backend_central_controle_fogo.dto.occurrenceReport;

import central_controle_fogo.com.backend_central_controle_fogo.Enum.OccurrenceStatus;
import central_controle_fogo.com.backend_central_controle_fogo.model.generic.Address;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class OccurrenceResponseDTO {

//    private LocalDateTime createdAt
//    private LocalDateTime updatedAt;

    // Campos da Fase 1
    private boolean occurrenceHasVictims;
    private boolean occurrenceIsPriority;
    private String occurrenceRequester;
    private String occurrenceRequesterPhoneNumber;
    private OccurrenceSubTypeResponseDTO occurrenceSubType;
    private Address address;

    // Campo de Controle
    private OccurrenceStatus status;

    // Campos da Fase 2
    private String occurrenceDetails;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private LocalDateTime occurrenceArrivalTime;
    private String involvedPeople;
    //    private String involvedVehicles;

}