package cotato.backend.application.port.out;

import cotato.backend.domain.Admin;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoadAdminPort extends JpaRepository<Admin, UUID> {
}
