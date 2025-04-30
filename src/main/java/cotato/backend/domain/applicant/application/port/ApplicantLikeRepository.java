package cotato.backend.domain.applicant.application.port;

public interface ApplicantLikeRepository {
	Long countByApplicantId(Long applicantId);
}