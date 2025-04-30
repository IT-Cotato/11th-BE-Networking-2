package cotato.backend.domain.applicant.application;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cotato.backend.api.applicant.dto.response.ApplicantGetResponse;
import cotato.backend.common.dto.PageResponse;
import cotato.backend.common.exception.EntityNotFoundException;
import cotato.backend.common.exception.ErrorCode;
import cotato.backend.domain.applicant.application.port.ApplicantRepository;
import cotato.backend.domain.applicant.domain.Applicant;
import cotato.backend.domain.applicant.dto.CreateApplicant;
import cotato.backend.domain.applicant.enums.SortType;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ApplicantService {

	private final ApplicantRepository applicantRepository;
	private final ApplicantLikeService applicantLikeService;

	@Transactional
	public Long save(CreateApplicant createApplicant) {
		return applicantRepository.save(Applicant.createNew(createApplicant));
	}

	public ApplicantGetResponse getApplicant(Long id) {
		Applicant applicant = applicantRepository.findById(id)
			.orElseThrow(() -> new EntityNotFoundException(ErrorCode.APPLICANT_NOT_FOUND_EXCEPTION));

		Long likes = applicantLikeService.getLikes(id);

		return ApplicantGetResponse.of(applicant, likes);
	}

	public PageResponse<ApplicantGetResponse> getApplicants(int page, int size, SortType sortType) {
		// 정렬 기준이 좋아요 수인 경우는 따로 처리
		if (sortType == SortType.MOST_LIKED) {
			return getMostLikedApplicants(page, size);
		}

		Pageable pageable = PageRequest.of(page, size, sortType.getSort());
		return PageResponse.of(applicantRepository.findAll(pageable)
			.map(applicant -> {
					Long likes = applicantLikeService.getLikes(applicant.getId());
					return ApplicantGetResponse.of(applicant, likes);
				}
			)
		);
	}

	private PageResponse<ApplicantGetResponse> getMostLikedApplicants(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);

		// 좋아요 수가 많은 순대로 정렬된 ID 목록을 가져옴
		Page<Long> idPage = applicantLikeService.countMostLikedApplicantIds(pageable);
		List<Long> ids = idPage.getContent();

		// 해당 ID 목록으로 지원자 정보를 맵으로 가져옴
		Map<Long, Applicant> applicantMap = applicantRepository.findByIdIn(ids).stream()
			.collect(Collectors.toMap(Applicant::getId, a -> a));

		// 원래 정렬 순서와 좋아요 수 유지하며 응답 생성
		List<ApplicantGetResponse> responses = ids.stream()
			.map(id -> {
				Applicant applicant = applicantMap.get(id);
				Long likes = applicantLikeService.getLikes(id);
				return ApplicantGetResponse.of(applicant, likes);
			})
			.collect(Collectors.toList());

		return PageResponse.of(new PageImpl<>(responses, pageable, applicantRepository.count()));
	}
}