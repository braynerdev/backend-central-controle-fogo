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

    @Column(nullable = false)
    @NotBlank(message = "Insira se existem vítimas")
    @Setter
    private boolean occurrenceHasVictims;

    @Column(length = 100)
    @Setter
    private String occurrenceLocationType; // ver se isso é viável, pq a agente nao tem como mapear todos os possiveis locais, e já temos o endereco

    @Column(length = 100)
    @Setter
    private String occurrenceAdditionalRisks; // isso é o grau da ocorrência? se for, fazer um enum exemplo (leve, Médio, alto, extremo).

    @Column(nullable = false)
    @NotBlank(message = "Insira se o atendimento é prioritário")
    @Setter
    private boolean occurrenceIsPriority; // isso é o prioridade da ocorrência? se for, fazer um enum exemplo (leve, Médio, alto, extremo).

    // levando em conta esses dois ultimos (occurrenceAdditionalRisks, occurrenceIsPriority) eles são bem parecidos. Acho melhor a gente tirar. Fala com thiago para deixar sõ um deles
    //Dados do solicitante

    @Column(nullable = false, length = 50) //pode ser nula, existem solicitacoes que a pessoa não se identifica
    @NotBlank(message = "Insira o nome do solicitante")
    private String occurrenceRequester;

    @Column(nullable = false, length = 11)
    @NotBlank(message = "Insira o telefone para contato do solicitante")
    @Setter
    private String occurrenceRequesterPhoneNumber;

    @ManyToOne()
    @JoinColumn(name = "ocurrency_type_id")
    private OcurrenceType occurrenceType;

    // adicionar um relacionamento com a tabela de user,
    // para identificar quem pegou a ocorrencia.
    // Pode ser que em uma ocorrencia tenha varios users !!!!
}
