package central_controle_fogo.com.backend_central_controle_fogo.dto.patent;

import central_controle_fogo.com.backend_central_controle_fogo.repository.patent.IPatentRepository;
import central_controle_fogo.com.backend_central_controle_fogo.validation.annotation.Unique;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatentResquestDTO {

    @NotBlank(message = "Nome da patente não aceita valores nulos")
    @Size(max = 100, message = "O nome da patente ter de 3 a 100 caracteres")
    @Unique(repository = IPatentRepository.class, field = "name", message = "Já existe uma patente cadastrada com esse nome.")
    private String name;
}
