package cotato.backend.domain.applicant.application.port;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cotato.backend.domain.applicant.domain.ApplicantLike;

public interface ApplicantLikeRepository {
	Long countByApplicantId(Long applicantId);

	Long save(ApplicantLike applicantLike);

	Page<Long> countMostLikedApplicantIds(Pageable pageable);
}