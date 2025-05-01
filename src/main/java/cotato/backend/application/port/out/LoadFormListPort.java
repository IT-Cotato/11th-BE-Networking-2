package cotato.backend.application.port.out;

import cotato.backend.domain.Form;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoadFormListPort extends JpaRepository<Form, UUID> {
    Page<Form> findAllByOrderBySubmittedAtDesc(Pageable pageable);

    Page<Form> findAllByOrderBySubmittedAtAsc(Pageable pageable);

    Page<Form> findAllByOrderByEngagementDescSubmittedAtDesc(Pageable pageable);

    Page<Form> findAllByOrderByGrowthDesireDescSubmittedAtDesc(Pageable pageable);
}
