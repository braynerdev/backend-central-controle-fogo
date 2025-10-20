package central_controle_fogo.com.backend_central_controle_fogo.model.occurrenceReport;


import central_controle_fogo.com.backend_central_controle_fogo.model.Base;
import central_controle_fogo.com.backend_central_controle_fogo.model.generic.Address;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "Occurrence")
@Getter
@NoArgsConstructor
public class Occurrence extends Base {

    //Dados da ocorrência

    @Column(nullable = false)
    @Setter
    @NotBlank(message = "Selecione se há vítimas")
    private boolean occurrenceHasVictims;


    @Column(nullable = false)
    @Setter
    @NotBlank(message = "Selecione se há prioridade no atendimento")
    private boolean occurrenceIsPriority;


    @Column(nullable = false, length = 50)
    @NotBlank(message = "Insira o nome do solicitante")
    private String occurrenceRequester;

    @Column(nullable = false, length = 11)
    @NotBlank(message = "Insira o telefone para contato do solicitante")
    @Setter
    private String occurrenceRequesterPhoneNumber;


    @ManyToOne()
    @JoinColumn(name = "ocurrency_type_id")
    private OccurrenceType occurrenceType;

    @ManyToOne()
    @JoinColumn(name = "ocurrency_sub_type_id")
    private OccurrenceSubType occurrenceSubType;


    @Setter
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @Column(length = 2000)
    @NotBlank(message = "Descreva detalhes da ocorrência")
    @Setter
    private String occurrenceDetails;

    //Localização GPS

    @Column
    @NotBlank(message = "A latitude não pode ser nula")
    @DecimalMin(value = "-90.0", message = "Latitude inválida (mínimo -90.0)")
    @DecimalMax(value = "90.0", message = "Latitude inválida (máximo 90.0)")
    private BigDecimal latitude;

    @Column
    @DecimalMin(value = "-180.0", message = "Longitude inválida (mínimo -180.0)")
    @DecimalMax(value = "180.0", message = "Longitude inválida (máximo 180.0)")
    private BigDecimal longitude;

    // adicionar um relacionamento com a tabela de user,
    // para identificar quem pegou a ocorrencia.
    // Pode ser que em uma ocorrencia tenha varios users !!!!
}
