package cotato.backend.domain.application.api.dto;

public record ApplicationLikeResult(
	Long applicationId,
	int likeCount
){
	public static ApplicationLikeResult of(Long applicationId, int likeCount) {
		return new ApplicationLikeResult(
			applicationId,
			likeCount
		);
	}
}
