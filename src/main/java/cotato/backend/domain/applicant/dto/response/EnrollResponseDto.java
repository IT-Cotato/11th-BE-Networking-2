package cotato.backend.domain.applicant.dto.response;

import cotato.backend.domain.applicant.entity.Applicant;

public record EnrollResponseDto(
	Long id,
	String name
) {
	public static EnrollResponseDto from(Applicant applicant) {
		return new EnrollResponseDto(
			applicant.getId(),
			applicant.getName()
		);
	}
}
