package cotato.backend.domain.recruitment.application;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cotato.backend.common.exception.EntityNotFoundException;
import cotato.backend.common.exception.ErrorCode;
import cotato.backend.domain.recruitment.api.dto.SubmissionSummary;
import cotato.backend.domain.recruitment.api.dto.response.LikeResponse;
import cotato.backend.domain.recruitment.api.dto.response.SubmissionDetailResponse;
import cotato.backend.domain.recruitment.dao.SubmissionRepository;
import cotato.backend.domain.recruitment.entity.SubmissionEntity;
import cotato.backend.domain.recruitment.enums.Part;
import cotato.backend.domain.recruitment.enums.SortType;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RecruitmentService {

	private final SubmissionRepository submissionRepository;

	@Transactional
	public Long apply(
		String name,
		Integer generation,
		Integer age,
		Part part,
		Integer participationScore,
		Integer growthMotivation,
		String phoneNumber
	) {
		SubmissionEntity submissionEntity = SubmissionEntity.builder()
			.name(name)
			.generation(generation)
			.age(age)
			.part(part)
			.participationScore(participationScore)
			.growthMotivation(growthMotivation)
			.phoneNumber(phoneNumber)
			.build();

		SubmissionEntity savedEntity = submissionRepository.save(submissionEntity);
		return savedEntity.getId();
	}

	@Transactional
	public LikeResponse addLike(Long applicationId) {
		SubmissionEntity submissionEntity = submissionRepository.findById(applicationId)
			.orElseThrow(() -> new EntityNotFoundException(ErrorCode.SUBMISSION_NOT_FOUND));

		submissionEntity.incrementLikeCount();
		return LikeResponse.of(submissionEntity.getId(), submissionEntity.getLikeCount());
	}

	public Page<SubmissionSummary> getSubmissions(
		int page,
		int size,
		SortType sortType
	) {
		Pageable pageable = PageRequest.of(page, size, sortType.toSort());
		Page<SubmissionEntity> applicationEntityPage = submissionRepository.findAll(pageable);

		return applicationEntityPage.map(application -> SubmissionSummary.of(
			application.getId(),
			application.getGeneration(),
			application.getPart(),
			application.getParticipationScore(),
			application.getGrowthMotivation(),
			application.getLikeCount(),
			application.getSubmissionTime()
		));
	}

	public SubmissionDetailResponse getSubmissionDetail(Long submissionId) {
		SubmissionEntity submissionEntity = submissionRepository.findById(submissionId)
			.orElseThrow(() -> new EntityNotFoundException(ErrorCode.SUBMISSION_NOT_FOUND));

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
}
