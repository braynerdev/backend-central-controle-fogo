package central_controle_fogo.com.backend_central_controle_fogo.repository.auth;

import central_controle_fogo.com.backend_central_controle_fogo.model.auth.Roles;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRolesRepository extends JpaRepository<Roles, Long> {

    Optional<Roles> findByName(String name);

    @Query("SELECT r FROM Roles r WHERE " +
            "(:active IS NULL OR r.active = :active) AND " +
            "(:filterGeneric IS NULL OR :filterGeneric = '' OR " +
            "LOWER(r.name) LIKE LOWER(CONCAT('%', :filterGeneric, '%')) OR " +
            "LOWER(r.description) LIKE LOWER(CONCAT('%', :filterGeneric, '%')))")
    Page<Roles> findAllWithFilters(
            @Param("active") Boolean active,
            @Param("filterGeneric") String filterGeneric,
            Pageable pageable
    );
}
