package cotato.backend.domain.application.api.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import cotato.backend.domain.application.enums.Part;

public record ApplicationSummary(
	long applicationId,
	int generation,
	Part part,
	int participationScore,
	int growthMotivation,
	int likeCount,
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	LocalDateTime submissionTime
) {
	public static ApplicationSummary of(
		long applicationId,
		int generation,
		Part part,
		int participationScore,
		int growthMotivation,
		int likeCount,
		LocalDateTime submissionTime
	) {
		return new ApplicationSummary(
			applicationId,
			generation,
			part,
			participationScore,
			growthMotivation,
			likeCount,
			submissionTime
		);
	}
}
