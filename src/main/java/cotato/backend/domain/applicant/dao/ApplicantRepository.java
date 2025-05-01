package cotato.backend.domain.applicant.dao;

import cotato.backend.domain.applicant.entity.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicantRepository extends JpaRepository<Applicant, Long> {
}
