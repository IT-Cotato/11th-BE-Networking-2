package cotato.backend.domain.recruitment.api.dto.response;

public record LikeResponse(
	Long applicationId,
	Integer likeCount
) {
	public static LikeResponse of(Long applicationId, Integer likeCount) {
		return new LikeResponse(applicationId, likeCount);
	}
}
