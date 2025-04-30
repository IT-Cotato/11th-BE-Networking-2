package cotato.backend.domain.applicant.dto.response;

import cotato.backend.domain.applicant.entity.Applicant;
import cotato.backend.domain.utils.DataTimeUtils;

public record ApplicantResponseDto(
	Long id,
	int generation,
	String part,
	int activity,
	int growth,
	int likes,
	String submissionTime
) {
	public static ApplicantResponseDto from(Applicant applicant) {
		return new ApplicantResponseDto(
			applicant.getId(),
			applicant.getGeneration(),
			applicant.getPart().getName(),
			applicant.getActivity(),
			applicant.getGrowth(),
			applicant.getLikes(),
			DataTimeUtils.format(applicant.getCreatedAt())
		);
	}
}
