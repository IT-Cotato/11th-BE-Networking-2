package cotato.backend.domain.applicant.dto.response;

import java.time.LocalDateTime;

import cotato.backend.domain.applicant.entity.Applicant;

public record ApplicantResponseDto(
	Long id,
	int generation,
	String part,
	int activity,
	int growth,
	int likes,
	LocalDateTime submissionTime
) {
	public static ApplicantResponseDto from(Applicant applicant) {
		return new ApplicantResponseDto(
			applicant.getId(),
			applicant.getGeneration(),
			applicant.getPart().getName(),
			applicant.getActivity(),
			applicant.getGrowth(),
			applicant.getLikes(),
			applicant.getCreatedAt()
		);
	}
}
