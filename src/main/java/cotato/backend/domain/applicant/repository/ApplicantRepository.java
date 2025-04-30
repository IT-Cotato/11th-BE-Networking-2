package cotato.backend.domain.applicant.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cotato.backend.domain.applicant.entity.Applicant;

public interface ApplicantRepository extends JpaRepository<Applicant, Long> {
}