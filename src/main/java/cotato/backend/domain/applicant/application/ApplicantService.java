package cotato.backend.domain.applicant.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cotato.backend.domain.applicant.application.port.ApplicantRepository;
import cotato.backend.domain.applicant.domain.Applicant;
import cotato.backend.domain.applicant.dto.CreateApplicant;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ApplicantService {

	private final ApplicantRepository applicantRepository;

	@Transactional
	public Long save(CreateApplicant createApplicant) {
		return applicantRepository.save(Applicant.createNew(createApplicant));
	}
}
