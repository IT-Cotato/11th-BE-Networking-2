package cotato.backend.domain.applicant.application.port;

import cotato.backend.domain.applicant.domain.ApplicantLike;

public interface ApplicantLikeRepository {
	Long countByApplicantId(Long applicantId);

	Long save(ApplicantLike applicantLike);
}