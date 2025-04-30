package cotato.backend.domain.applicant.dto;

import cotato.backend.domain.applicant.enums.PartType;

public record CreateApplicant(
	String name,
	Long generation,
	Long age,
	PartType part,
	Long participationLevel,
	Long growthWillingness,
	String phoneNumber
) {
}