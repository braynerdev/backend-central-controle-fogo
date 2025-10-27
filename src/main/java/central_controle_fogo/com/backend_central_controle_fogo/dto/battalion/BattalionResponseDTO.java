package central_controle_fogo.com.backend_central_controle_fogo.dto.battalion;

import central_controle_fogo.com.backend_central_controle_fogo.dto.address.AddressResponseDTO;
import central_controle_fogo.com.backend_central_controle_fogo.model.auth.User;
import central_controle_fogo.com.backend_central_controle_fogo.model.generic.Address;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class BattalionResponseDTO {

    private String name;

    private String phoneNumber;

    private String email;

    private AddressResponseDTO endereco;
}
