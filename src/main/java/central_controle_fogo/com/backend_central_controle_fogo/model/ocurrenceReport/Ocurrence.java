package central_controle_fogo.com.backend_central_controle_fogo.model.ocurrenceReport;


import central_controle_fogo.com.backend_central_controle_fogo.model.Base;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Ocurrence")
@Getter
@NoArgsConstructor
public class Ocurrence extends Base {

    //Dados da ocorrência

    @Column(unique = true, nullable = false, length = 256)
    @NotBlank(message = "Informe o tipo de ocorrência")
    @Size(min = 5, max = 256)
    private String occurrenceType;

    @Column(nullable = false)
    @NotBlank(message = "Insira se existem vítimas")
    private boolean occurrenceHasVictims;

    //Local da ocorrência

    @Column(nullable = false, length = 256)
    @NotBlank(message = "Insira o enderaço da ocorrência")
    private String occurrenceAddress;

    @Column(length = 256)
    private String occurrenceAddressReference;

    @Column(length = 100)
    private String occurrenceLocationType;

    @Column(length = 100)
    private String occurrenceAdditionalRisks;

    @Column(nullable = false)
    @NotBlank(message = "Insira se o atendimento é prioritário")
    private boolean occurrenceIsPriority;

    //Dados do solicitante

    @Column(nullable = false, length = 50)
    @NotBlank(message = "Insira o nome do solicitante")
    private String occurrenceRequester;

    @Column(nullable = false, length = 11)
    @NotBlank(message = "Insira o telefone para contato do solicitante")
    private String occurrenceRequesterPhoneNumber;
}
