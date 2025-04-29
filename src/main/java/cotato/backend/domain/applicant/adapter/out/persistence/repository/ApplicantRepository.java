package cotato.backend.domain.applicant.adapter.out.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cotato.backend.domain.applicant.adapter.out.persistence.entity.ApplicantJpaEntity;
import jakarta.persistence.LockModeType;

public interface ApplicantRepository extends JpaRepository<ApplicantJpaEntity, Long> {

	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("SELECT a FROM ApplicantJpaEntity a WHERE a.id = :id")
	Optional<ApplicantJpaEntity> findByIdWithLock(@Param("id") Long id);
}
