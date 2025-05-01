package cotato.backend.application.port.out;

import cotato.backend.domain.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddLikePort extends JpaRepository<Like, Long> {
}
