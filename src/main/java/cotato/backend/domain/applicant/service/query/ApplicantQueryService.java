package cotato.backend.domain.applicant.service.query;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cotato.backend.common.exception.ErrorCode;
import cotato.backend.domain.applicant.dto.response.ApplicantResponseDto;
import cotato.backend.domain.applicant.dto.response.SearchDetailsResponseDto;
import cotato.backend.domain.applicant.entity.Applicant;
import cotato.backend.domain.applicant.exception.ApplicantException;
import cotato.backend.domain.applicant.repository.ApplicantRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ApplicantQueryService {
	private final ApplicantRepository applicantRepository;

	public SearchDetailsResponseDto findById(Long id) {
		Applicant findedApplicant = applicantRepository.findById(id)
			.orElseThrow(() -> new ApplicantException(ErrorCode.APPLICANT_NOT_FOUND));
		return SearchDetailsResponseDto.from(findedApplicant);
	}

	@Cacheable(value = "applicants", key = "#pageable.pageNumber + '_' + #pageable.pageSize + '_' + #pageable.sort")
	public Page<ApplicantResponseDto> findApplicantsSortedBy(Pageable pageable) {
		Page<Applicant> applicants = applicantRepository.findAll(pageable);
		return applicants.map(ApplicantResponseDto::from);
	}
}
