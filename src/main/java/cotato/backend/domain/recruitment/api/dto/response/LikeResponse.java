package cotato.backend.domain.recruitment.api.dto.response;

import cotato.backend.domain.recruitment.api.dto.SubmissionLikeResult;

public record LikeResponse(
	Long applicationId,
	Integer likeCount
) {
	public static LikeResponse from(SubmissionLikeResult result) {
		return new LikeResponse(
			result.applicationId(),
			result.likeCount()
		);
	}
}
