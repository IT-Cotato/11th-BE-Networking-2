package cotato.backend.domain.recruitment.api.dto;

public record SubmissionLikeResult(
	Long applicationId,
	int likeCount
){
	public static SubmissionLikeResult of(Long applicationId, int likeCount) {
		return new SubmissionLikeResult(
			applicationId,
			likeCount
		);
	}
}
