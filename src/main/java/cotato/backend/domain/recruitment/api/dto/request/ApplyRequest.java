package cotato.backend.domain.recruitment.api.dto.request;

import cotato.backend.domain.recruitment.enums.Part;

public record ApplyRequest(
	String name,
	Integer generation,
	Integer age,
	Part part,
	Integer participationScore,
	Integer growthMotivation,
	String phoneNumber
) {
}
