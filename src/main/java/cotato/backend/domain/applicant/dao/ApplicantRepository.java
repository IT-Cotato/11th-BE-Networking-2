package cotato.backend.domain.applicant.dao;

import cotato.backend.domain.applicant.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ApplicantRepository extends JpaRepository<Applicant, Long> {
    Optional<Applicant> findByPhoneNumber(String phoneNumber);
    List<Applicant> findAll();
}
