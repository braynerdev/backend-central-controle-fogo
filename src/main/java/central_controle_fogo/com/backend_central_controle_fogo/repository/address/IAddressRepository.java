package central_controle_fogo.com.backend_central_controle_fogo.repository.address;

import central_controle_fogo.com.backend_central_controle_fogo.model.generic.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IAddressRepository extends JpaRepository<Address, Long> {
}
