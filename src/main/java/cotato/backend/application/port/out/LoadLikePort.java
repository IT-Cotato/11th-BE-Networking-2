package cotato.backend.application.port.out;

import cotato.backend.domain.Admin;
import cotato.backend.domain.Form;
import cotato.backend.domain.Like;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoadLikePort extends JpaRepository<Like, Long> {
    Optional<Like> findByFormAndAdmin(Form form, Admin admin);
}
