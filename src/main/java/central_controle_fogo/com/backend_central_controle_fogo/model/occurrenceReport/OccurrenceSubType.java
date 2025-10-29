//package central_controle_fogo.com.backend_central_controle_fogo.model.occurrenceReport;
//
//import central_controle_fogo.com.backend_central_controle_fogo.model.Base;
//import jakarta.persistence.*;
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.Size;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import java.util.List;
//
//@Entity
//@Table(name = "occurrence_sub_type")
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//public class OccurrenceSubType extends Base {
//    @Column(length = 100, nullable = false)
//    @NotBlank(message = "Tipo de ocorrência não pode ser nulo")
//    @Size(max = 100, message = "Tipo de ocorrência pode ter no máximo 100 caracters")
//    private String name;
//
//    @ManyToOne
//    @JoinColumn(name = "occurrence_type_id", nullable = false)
//    private OccurrenceType occurrenceType;
//
//    @OneToMany(mappedBy = "occurrenceSubType")
//    private List<Occurrence> occurrences;
//
//    public OccurrenceSubType(String name, OccurrenceType occurrenceType) {
//        this.name = name;
//        this.occurrenceType = occurrenceType;
//    }
//
//}