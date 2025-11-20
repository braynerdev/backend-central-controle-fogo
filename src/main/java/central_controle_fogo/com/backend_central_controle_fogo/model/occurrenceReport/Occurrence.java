package central_controle_fogo.com.backend_central_controle_fogo.model.occurrenceReport;

import central_controle_fogo.com.backend_central_controle_fogo.model.Base;
import central_controle_fogo.com.backend_central_controle_fogo.model.battalion.Battalion;
import central_controle_fogo.com.backend_central_controle_fogo.model.generic.Address;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Table(name = "Occurrence")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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


    @ManyToOne
    @JoinColumn(name = "occurrence_sub_type_id", nullable = false)
    @NotNull(message = "Insira o tipo da ocorrência")
    private OccurrenceSubType occurrenceSubType;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    @NotNull(message = "O endereço é obrigatório")
    private Address address;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private OccurrenceStatus status;

    @Column(length = 2000)
    private String occurrenceDetails;
    
    @Column(precision = 10, scale = 7)
    private BigDecimal latitude;

    @Column(precision = 11, scale = 7)
    private BigDecimal longitude;

    @Column
    private OffsetDateTime occurrenceArrivalTime;

    @OneToMany(mappedBy = "occurrence", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<OccurrenceVehicles> occurrenceVehicles;

    @OneToMany(mappedBy = "occurrence", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<OccurrenceUsers> users;

    @OneToMany(mappedBy = "occurrence", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<OccurrenceBattalions> battalions;

    @OneToMany(mappedBy = "occurrence", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<OccurrencePhotos> photos;

    public Occurrence(boolean occurrenceHasVictims, String occurrenceRequester, String occurrenceRequesterPhoneNumber, OccurrenceSubType occurrenceSubType, Address address, OccurrenceStatus status) {
        this.occurrenceHasVictims = occurrenceHasVictims;
        this.occurrenceRequester = occurrenceRequester;
        this.occurrenceRequesterPhoneNumber = occurrenceRequesterPhoneNumber;
        this.occurrenceSubType = occurrenceSubType;
        this.address = address;
        this.status = status;
    }

    public Occurrence(String occurrenceDetails, BigDecimal latitude, BigDecimal longitude, OffsetDateTime occurrenceArrivalTime, List<OccurrenceUsers> users, List<OccurrenceVehicles> occurrenceVehicles, OccurrenceStatus status, List<OccurrenceBattalions> battalions, List<OccurrencePhotos> photos) {
        this.occurrenceDetails = occurrenceDetails;
        this.latitude = latitude;
        this.longitude = longitude;
        this.occurrenceArrivalTime = occurrenceArrivalTime;
        this.users = users;
        this.occurrenceVehicles = occurrenceVehicles;
        this.status = status;
        this.battalions = battalions;
        this.photos = photos;
    }
}
