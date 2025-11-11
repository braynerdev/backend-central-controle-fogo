package central_controle_fogo.com.backend_central_controle_fogo.dto.reports;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReportsMapRequestDTO {
    private OffsetDateTime dataInicial;
    private OffsetDateTime dataFinal;
    private String natureza;
}
