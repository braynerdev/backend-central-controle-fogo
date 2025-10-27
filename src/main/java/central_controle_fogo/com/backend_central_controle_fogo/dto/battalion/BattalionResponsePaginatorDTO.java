package central_controle_fogo.com.backend_central_controle_fogo.dto.battalion;

import central_controle_fogo.com.backend_central_controle_fogo.model.auth.User;
import central_controle_fogo.com.backend_central_controle_fogo.model.generic.Address;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class BattalionResponsePaginatorDTO {

    private Long id;

    private boolean active;

    private String name;

    private String phoneNumber;

    private String email;

}
