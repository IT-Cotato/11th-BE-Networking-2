package cotato.backend.domain.application.api.dto.response;

import cotato.backend.domain.application.api.dto.ApplicationLikeResult;

public record LikeResponse(
	Long applicationId,
	Integer likeCount
) {
	public static LikeResponse from(ApplicationLikeResult result) {
		return new LikeResponse(
			result.applicationId(),
			result.likeCount()
		);
	}
}
