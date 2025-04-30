package cotato.backend.application.port.out;

import cotato.backend.domain.Form;
import cotato.backend.domain.Like;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LoadFormListByLikePort extends JpaRepository<Like, Long> {
    @Query("SELECT l FROM Like l GROUP BY l.form ORDER BY count(l) DESC")
    Page<Form> findFormListByLike(Pageable pageable);
}
