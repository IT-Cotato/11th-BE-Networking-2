package cotato.backend.domain.applicant.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import cotato.backend.domain.applicant.entity.ApplicantLikeEntity;

public interface ApplicantLikeJpaRepository extends JpaRepository<ApplicantLikeEntity, Long> {

	Long countByApplicantId(Long applicantId);
}