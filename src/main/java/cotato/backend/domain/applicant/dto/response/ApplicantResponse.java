package cotato.backend.domain.applicant.dto.response;

import cotato.backend.domain.applicant.entity.Applicant;

public record ApplicantResponse(
	Long id,
	String name
) {
	public static ApplicantResponse from(Applicant applicant) {
		return new ApplicantResponse(
			applicant.getId(),
			applicant.getName()
		);
	}
}
