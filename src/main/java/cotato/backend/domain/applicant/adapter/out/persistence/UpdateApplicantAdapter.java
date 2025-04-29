package cotato.backend.domain.applicant.adapter.out.persistence;

import org.springframework.stereotype.Component;

import cotato.backend.common.exception.ErrorCode;
import cotato.backend.domain.applicant.adapter.out.persistence.entity.ApplicantJpaEntity;
import cotato.backend.domain.applicant.adapter.out.persistence.repository.ApplicantRepository;
import cotato.backend.domain.applicant.application.port.out.UpdateApplicantPort;
import cotato.backend.domain.applicant.exception.ApplicantException;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UpdateApplicantAdapter implements UpdateApplicantPort {

	private final ApplicantRepository applicantRepository;

	@Override
	public void increaseLikeCount(final long applicantId) {
		ApplicantJpaEntity entity = applicantRepository.findByIdWithLock(applicantId)
			.orElseThrow(() -> new ApplicantException(ErrorCode.APPLICANT_NOT_FOUND));

		entity.increaseLikes();
	}
}
