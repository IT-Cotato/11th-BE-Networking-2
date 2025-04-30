package cotato.backend.domain.recruitment.api.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import cotato.backend.domain.recruitment.enums.Part;

public record SubmissionSummary(
	long applicationId,
	int generation,
	Part part,
	int participationScore,
	int growthMotivation,
	int likeCount,
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	LocalDateTime submissionTime
) {
	public static SubmissionSummary of(
		long applicationId,
		int generation,
		Part part,
		int participationScore,
		int growthMotivation,
		int likeCount,
		LocalDateTime submissionTime
	) {
		return new SubmissionSummary(
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
