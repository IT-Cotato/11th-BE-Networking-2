package cotato.backend.api.applicant.dto.response;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import cotato.backend.domain.applicant.domain.Applicant;

public record ApplicantGetResponse(
	Long id,
	Long generation,
	String part,
	Long participationLevel,
	Long growthWillingness,
	Long likes,
	String phoneNumber,
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	LocalDateTime submissionTime
) {
	public static ApplicantGetResponse of(Applicant applicant, Long likes) {
		return new ApplicantGetResponse(
			applicant.getId(),
			applicant.getGeneration(),
			applicant.getPart().getDescription(),
			applicant.getParticipationLevel(),
			applicant.getGrowthWillingness(),
			likes,
			applicant.getPhoneNumber(),
			applicant.getSubmissionTime()
		);
	}
}
