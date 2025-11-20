package central_controle_fogo.com.backend_central_controle_fogo.dto.occurrenceReport.occurrenceSecond;

import central_controle_fogo.com.backend_central_controle_fogo.dto.address.AddressRegisterDTO;
import central_controle_fogo.com.backend_central_controle_fogo.dto.auth.UserResponseDTO;
import central_controle_fogo.com.backend_central_controle_fogo.dto.vehicle.VehicleResponseDTO;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OccurrenceSecondResponseDTO {
    private Long id;

    private OffsetDateTime createdAt;

    private OffsetDateTime updatedAt;

    private boolean active;

    private boolean occurrenceHasVictims;

    private String occurrenceRequester;

    private String occurrenceRequesterPhoneNumber;

    private String occurrenceNature;

    private String occurrenceType;

    private String occurrenceSubType;

    private AddressRegisterDTO address;

    private String status;

    private String occurrenceDetails;

    private BigDecimal latitude;

    private BigDecimal longitude;

    private OffsetDateTime occurrenceArrivalTime;

    private List<UserResponseDTO> users;

    private List<VehicleResponseDTO> vehicles;

    private List<String> battalions;

    private List<String> photoUrls;
}
