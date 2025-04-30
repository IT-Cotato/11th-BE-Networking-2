package cotato.backend.domain.recruitment.application;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import cotato.backend.domain.recruitment.api.dto.SubmissionSummary;
import cotato.backend.domain.recruitment.api.dto.response.SubmissionDetailResponse;
import cotato.backend.domain.recruitment.entity.SubmissionEntity;

@Component
public class SubmissionDtoMapper {

	private SubmissionSummary toSummary(SubmissionEntity submissionEntity) {
		return SubmissionSummary.of(
			submissionEntity.getId(),
			submissionEntity.getGeneration(),
			submissionEntity.getPart(),
			submissionEntity.getParticipationScore(),
			submissionEntity.getGrowthMotivation(),
			submissionEntity.getLikeCount(),
			submissionEntity.getSubmissionTime()
		);
	}

	public SubmissionDetailResponse toDetailResponse(SubmissionEntity submissionEntity) {
		return SubmissionDetailResponse.of(
			submissionEntity.getId(),
			submissionEntity.getGeneration(),
			submissionEntity.getPart(),
			submissionEntity.getParticipationScore(),
			submissionEntity.getGrowthMotivation(),
			submissionEntity.getLikeCount(),
			submissionEntity.getPhoneNumber(),
			submissionEntity.getSubmissionTime()
		);
	}

	public Page<SubmissionSummary> toSummaryPage(Page<SubmissionEntity> submissionEntityPage) {
		return submissionEntityPage.map(this::toSummary);
	}
}
