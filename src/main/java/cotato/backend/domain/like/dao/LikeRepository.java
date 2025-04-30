package cotato.backend.domain.like.dao;

import cotato.backend.domain.admin.Admin;
import cotato.backend.domain.application.Application;
import cotato.backend.domain.like.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    boolean existsByAdminAndApplication(Admin admin, Application application);
    Optional<Like> findByAdminAndApplication(Admin admin, Application application);
    int countByApplication(Application application);
}
