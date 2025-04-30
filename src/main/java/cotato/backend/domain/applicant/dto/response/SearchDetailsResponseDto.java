package cotato.backend.domain.applicant.dto.response;

import java.time.LocalDateTime;

import cotato.backend.domain.applicant.entity.Applicant;

public record SearchDetailsResponseDto(
	Long id,
	int generation,
	String part,
	int activity,
	int growth,
	int likes,
	String phoneNumber,
	LocalDateTime submissionTime
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
			applicant.getCreatedAt()
		);
	}
}