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
@AllArgsConstructor
@NoArgsConstructor
public class Address extends Base {

    @Setter
    @Column(nullable = false, length = 100)
    @Size(max = 100, message = "O logradouro aceita no máximo 100 caracteres")
    @NotBlank(message = "Logradouro é obrigatório")
    private String street;

    @Setter
    @Column(nullable = false)
    @NotBlank(message = "O número é obrigatório")
    private int number;

    @Setter
    @Column(nullable = true, length = 100)
    @Size(max = 100, message = "Complemento deve ter no máximo 100 caracters")
    private String complement;

    @Setter
    @Column(nullable = false, length = 100)
    @Size(max = 100, message = "O bairro deve ter no máximo 100 caracters")
    @NotBlank(message = "O bairro é obrigatório")
    private String neighborhood;

    @Setter
    @Column(nullable = false, length = 100)
    @Size(max = 100, message = "A cidade deve ter no máximo 100 caracters")
    @NotBlank(message = "A cidade é obrigatório")
    private String city;

    @Setter
    @Column(nullable = false, length = 100)
    @Size(max = 100, message = "O estado deve ter no máximo 100 caracters")
    @NotBlank(message = "O estado é obrigatório")
    private String state;

    @Setter
    @Column(nullable = false, length = 8)
    @Size(max = 8, message = "O CEP deve ter 8 caracters")
    @NotBlank(message = "O CEP é obrigatório")
    private String zipCode;
}
