package central_controle_fogo.com.backend_central_controle_fogo.dto.address;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class AddressResponseDTO {

    private String street;

    private int number;

    private String complement;

    private String neighborhood;

    private String city;

    private String state;

    private String zipCode;
}
