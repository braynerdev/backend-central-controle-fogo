//package central_controle_fogo.com.backend_central_controle_fogo.model.vehicles;
//
//import central_controle_fogo.com.backend_central_controle_fogo.model.Base;
//import central_controle_fogo.com.backend_central_controle_fogo.model.battalion.Battalion;
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//@Entity
//@Table(name = "vehicles")
//@Getter
//@NoArgsConstructor
//public class Vehicle extends Base {
//
//    @Setter
//    @Column(unique = true, nullable = false, length = 30)
//    private String name;
//
//    @Setter
//    @ManyToOne
//    @JoinColumn(name = "battalion_id", nullable = false)
//    private Battalion battalion;
//}
