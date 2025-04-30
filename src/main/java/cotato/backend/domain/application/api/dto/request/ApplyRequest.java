package cotato.backend.domain.application.api.dto.request;

import cotato.backend.domain.example.entity.Part;

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
