package central_controle_fogo.com.backend_central_controle_fogo.model.generic;
import central_controle_fogo.com.backend_central_controle_fogo.model.Base;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "address")
@Getter
@NoArgsConstructor
public class Address extends Base {

    @Setter
    @Column(nullable = false, length = 100)
    private String street;

    @Setter
    @Column(nullable = false)
    private int number;

    @Setter
    @Column(nullable = true, length = 100)
    private String complement;

    @Setter
    @Column(nullable = false, length = 100)
    private String neighborhood;

    @Setter
    @Column(nullable = false, length = 100)
    private String city;

    @Setter
    @Column(nullable = false, length = 100)
    private String state;

    @Setter
    @Column(nullable = false, length = 8)
    private String zipCode;

    public Address(String street, int number, String complement, String neighborhood, String city, String state, String zipCode) {
        this.street = street;
        this.number = number;
        this.complement = complement;
        this.neighborhood = neighborhood;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }
}
