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
import lombok.Setter;

@Entity
@Table(name = "Ocurrence")
@Getter
@NoArgsConstructor
public class Ocurrence extends Base {

    //Dados da ocorrência

    @Column(unique = true, nullable = false, length = 256)
    @NotBlank(message = "Informe o tipo de ocorrência")
    @Size(min = 5, max = 256)
    @Setter
    private String occurrenceType;

    @Column(nullable = false)
    @NotBlank(message = "Insira se existem vítimas")
    @Setter
    private boolean occurrenceHasVictims;

    @Column(length = 100)
    @Setter
    private String occurrenceLocationType; // fazer um enum (fiz um dos nomes da permissão e uso ele lá no models roles)

    @Column(length = 100)
    @Setter
    private String occurrenceAdditionalRisks; // fazer um enum (fiz um dos nomes da permissão e uso ele lá no models roles)

    @Column(nullable = false)
    @NotBlank(message = "Insira se o atendimento é prioritário")
    @Setter
    private boolean occurrenceIsPriority;

    //Dados do solicitante

    @Column(nullable = false, length = 50) //pode ser nula, existem solicitacoes que a pessoa não se identifica
    @NotBlank(message = "Insira o nome do solicitante")
    private String occurrenceRequester;

    @Column(nullable = false, length = 11)
    @NotBlank(message = "Insira o telefone para contato do solicitante")
    @Setter
    private String occurrenceRequesterPhoneNumber;

    // adicionar um relacionamento com a tabela de user,
    // para identificar quem pegou a ocorrencia.
    // Pode ser que em uma ocorrencia tenha varios users
}
