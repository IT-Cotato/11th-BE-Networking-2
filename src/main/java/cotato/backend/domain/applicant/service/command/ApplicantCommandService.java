package cotato.backend.domain.applicant.service.command;

import static cotato.backend.common.exception.ErrorCode.*;

import org.springframework.stereotype.Service;

import cotato.backend.domain.applicant.dto.request.ApplicantRequestDto;
import cotato.backend.domain.applicant.dto.response.EnrollResponseDto;
import cotato.backend.domain.applicant.entity.Applicant;
import cotato.backend.domain.applicant.exception.ApplicantException;
import cotato.backend.domain.applicant.repository.ApplicantRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ApplicantCommandService {

	private final ApplicantRepository applicantRepository;

	public EnrollResponseDto save(ApplicantRequestDto request) {
		Applicant newApplicant = request.toEntity();
		applicantRepository.save(newApplicant);
		return EnrollResponseDto.from(newApplicant);
	}

	public void increaseLikes(Long id) {
		Applicant applicant = applicantRepository.findById(id)
			.orElseThrow(() -> new ApplicantException(APPLICANT_NOT_FOUND));
		applicant.addLike();
	}
}
