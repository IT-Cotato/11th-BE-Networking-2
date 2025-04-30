package cotato.backend.common.dto;

public record DefaultIdResponse(
	Long id
) {
	public static DefaultIdResponse of(Long id) {
		return new DefaultIdResponse(id);
	}
}