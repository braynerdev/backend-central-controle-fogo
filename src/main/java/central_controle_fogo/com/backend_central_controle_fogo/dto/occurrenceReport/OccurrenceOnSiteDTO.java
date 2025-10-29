package central_controle_fogo.com.backend_central_controle_fogo.dto.occurrenceReport;


import central_controle_fogo.com.backend_central_controle_fogo.Enum.OccurrenceStatus;
import central_controle_fogo.com.backend_central_controle_fogo.model.auth.User;
//import central_controle_fogo.com.backend_central_controle_fogo.model.vehicles.Vehicle;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OccurrenceOnSiteDTO {

    @NotBlank(message = "Descreva os detalhes da ocorrência")
    @Size(max = 2000)
    private String occurrenceDetails;

    @NotNull(message = "Latitude é obrigatória")

    private BigDecimal latitude;

    @NotNull(message = "Longitude é obrigatória")

    private BigDecimal longitude;

    @NotNull(message = "A hora da chegada é obrigatória")
    private LocalDateTime occurrenceArrivalTime;


    private List<User> userIds;

//    private List<Vehicle> vehicleIds;

    @NotNull(message = "É obrigatório definir um status final (ex: CONCLUIDA, FALSO_ALARME)")
    private OccurrenceStatus finalStatus;
}