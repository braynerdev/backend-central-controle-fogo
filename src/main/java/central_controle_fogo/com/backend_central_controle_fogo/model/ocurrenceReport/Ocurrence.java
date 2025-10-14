package central_controle_fogo.com.backend_central_controle_fogo.model.ocurrenceReport;


import central_controle_fogo.com.backend_central_controle_fogo.model.Base;
import jakarta.persistence.*;
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


    @ManyToOne()
    @JoinColumn(name = "ocurrency_type_id")
    private OcurrenceType occurrenceType;

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
