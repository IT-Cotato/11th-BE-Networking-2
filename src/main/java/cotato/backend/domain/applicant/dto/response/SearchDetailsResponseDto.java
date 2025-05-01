package cotato.backend.domain.applicant.dto.response;

import cotato.backend.domain.applicant.entity.Applicant;
import cotato.backend.domain.utils.DataTimeUtils;

public record SearchDetailsResponseDto(
	Long id,
	int generation,
	String part,
	int activity,
	int growth,
	int likes,
	String phoneNumber,
	String submissionTime
) {

	public static SearchDetailsResponseDto from(Applicant applicant) {
		return new SearchDetailsResponseDto(
			applicant.getId(),
			applicant.getGeneration(),
			applicant.getPart().getName(),
			applicant.getActivity(),
			applicant.getGrowth(),
			applicant.getLikes(),
			applicant.getPhoneNumber(),
			DataTimeUtils.format(applicant.getCreatedAt())
		);
	}
}