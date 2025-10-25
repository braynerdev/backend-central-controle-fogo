package central_controle_fogo.com.backend_central_controle_fogo.model.occurrenceReport;

import central_controle_fogo.com.backend_central_controle_fogo.Enum.OccurrenceStatus;
import central_controle_fogo.com.backend_central_controle_fogo.model.Base;
import central_controle_fogo.com.backend_central_controle_fogo.model.generic.Address;
import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Occurrence")
@Getter
@Setter
@NoArgsConstructor
public class Occurrence extends Base {

    // DADOS DA FASE 1
    @Column(nullable = false)
    @NotNull(message = "Selecione se há vítimas")
    private boolean occurrenceHasVictims;

    @Column(nullable = false)
    @NotNull(message = "Selecione se há prioridade no atendimento")
    private boolean occurrenceIsPriority;

    @Column(nullable = false, length = 50)
    @NotBlank(message = "Insira o nome do solicitante")
    private String occurrenceRequester;

    @Column(nullable = false, length = 11)
    @NotBlank(message = "Insira o telefone para contato do solicitante")
    private String occurrenceRequesterPhoneNumber;

    @ManyToOne
    @JoinColumn(name = "occurrence_sub_type_id")
    @NotNull(message = "O subtipo da ocorrência é obrigatório")
    private OccurrenceSubType occurrenceSubType;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    @NotNull(message = "O endereço é obrigatório")
    private Address address;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private OccurrenceStatus status;

    // DADOS DA FASE 2

    @Column(length = 2000)
    private String occurrenceDetails;
    @Column

    private BigDecimal latitude;

    @Column

    private BigDecimal longitude;

    @Column
    private LocalDateTime occurrenceArrivalTime;

    @Column(length = 500)
    private String involvedPeople;

    @Column(length = 500)
    private String involvedVehicles;

    @Column(length = 500)
    private String emergencyVehicles;
}


