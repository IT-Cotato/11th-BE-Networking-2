package cotato.backend.domain.application.dao;

import cotato.backend.domain.application.Application;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    Page<Application> findAll(Pageable pageable);
}
