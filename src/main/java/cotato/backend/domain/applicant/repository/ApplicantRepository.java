package cotato.backend.domain.applicant.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import cotato.backend.domain.applicant.entity.Applicant;
import jakarta.persistence.LockModeType;

public interface ApplicantRepository extends JpaRepository<Applicant, Long> {
	Optional<Applicant> findById(Long id);

	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("SELECT p FROM Applicant p WHERE p.id = :id")
	Optional<Applicant> findByIdForUpdate(Long id);
}