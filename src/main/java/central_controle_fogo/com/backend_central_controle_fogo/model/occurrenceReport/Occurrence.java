package central_controle_fogo.com.backend_central_controle_fogo.model.occurrenceReport;

import central_controle_fogo.com.backend_central_controle_fogo.Enum.OccurrenceStatus;
import central_controle_fogo.com.backend_central_controle_fogo.model.Base;
import central_controle_fogo.com.backend_central_controle_fogo.model.auth.UserRoles;
import central_controle_fogo.com.backend_central_controle_fogo.model.generic.Address;
import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Occurrence")
@Getter
@Setter
@NoArgsConstructor
public class Occurrence extends Base {

    @Column(nullable = false)
    @NotNull(message = "Selecione se há vítimas")
    private boolean occurrenceHasVictims;

    @Column(nullable = false, length = 50)
    @NotBlank(message = "Insira o nome do solicitante")
    private String occurrenceRequester;

    @Column(nullable = false, length = 11)
    @NotBlank(message = "Insira o telefone para contato do solicitante")
    private String occurrenceRequesterPhoneNumber;


    @Column(nullable = false, length = 100)
    @NotBlank(message = "Insira o tipo da ocorrência")
    private String occurrenceSubType;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    @NotNull(message = "O endereço é obrigatório")
    private Address address;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private OccurrenceStatus status;

    @Column(length = 2000)
    private String occurrenceDetails;
    
    @Column(precision = 10, scale = 7)
    private BigDecimal latitude;

    @Column(precision = 11, scale = 7)
    private BigDecimal longitude;

    @Column
    private LocalDateTime occurrenceArrivalTime;


    @OneToMany(mappedBy = "occurrence", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<OccurenceUsers> users;


    public Occurrence(boolean occurrenceHasVictims, String occurrenceRequester, String occurrenceRequesterPhoneNumber, String occurrenceSubType, Address address) {
        this.occurrenceHasVictims = occurrenceHasVictims;
        this.occurrenceRequester = occurrenceRequester;
        this.occurrenceRequesterPhoneNumber = occurrenceRequesterPhoneNumber;
        this.occurrenceSubType = occurrenceSubType;
        this.address = address;
        this.status = OccurrenceStatus.EM_ATENDIMENTO;
    }

    public Occurrence(String occurrenceDetails, BigDecimal latitude, BigDecimal longitude, LocalDateTime occurrenceArrivalTime, List<OccurenceUsers> users) {
        this.occurrenceDetails = occurrenceDetails;
        this.latitude = latitude;
        this.longitude = longitude;
        this.occurrenceArrivalTime = occurrenceArrivalTime;
        this.users = users;
        this.status = OccurrenceStatus.CONCLUIDA;
    }
}


