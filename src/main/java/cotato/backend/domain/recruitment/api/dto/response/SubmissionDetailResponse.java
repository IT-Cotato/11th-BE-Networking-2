package cotato.backend.domain.recruitment.api.dto.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import cotato.backend.domain.recruitment.enums.Part;

public record SubmissionDetailResponse(
	long applicationId,
	int generation,
	Part part,
	int participationScore,
	int growthMotivation,
	int likeCount,
	String phoneNumber,
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	LocalDateTime submissionTime
) {
	public static SubmissionDetailResponse of(
		long applicationId,
		int generation,
		Part part,
		int participationScore,
		int growthMotivation,
		int likeCount,
		String phoneNumber,
		LocalDateTime submissionTime
	) {
		return new SubmissionDetailResponse(
			applicationId,
			generation,
			part,
			participationScore,
			growthMotivation,
			likeCount,
			phoneNumber,
			submissionTime
		);
	}
}
