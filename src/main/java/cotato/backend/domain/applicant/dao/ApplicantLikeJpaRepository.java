package cotato.backend.domain.applicant.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import cotato.backend.domain.applicant.entity.ApplicantLikeEntity;

public interface ApplicantLikeJpaRepository extends JpaRepository<ApplicantLikeEntity, Long> {

	@Query("SELECT COUNT(al) FROM ApplicantLikeEntity al WHERE al.applicantId = :applicantId")
	Long countByApplicantId(Long applicantId);

	@Query(value = "SELECT al.applicant_id FROM applicant_like al GROUP BY al.applicant_id ORDER BY COUNT(al.id) DESC",
		countQuery = "SELECT COUNT(DISTINCT al.applicant_id) FROM applicant_like al",
		nativeQuery = true)
	Page<Long> countMostLikedApplicantIds(Pageable pageable);
}