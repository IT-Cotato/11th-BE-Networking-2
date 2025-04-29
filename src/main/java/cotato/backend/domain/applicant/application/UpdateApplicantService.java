package cotato.backend.domain.applicant.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cotato.backend.domain.applicant.application.port.in.UpdateApplicantUseCase;
import cotato.backend.domain.applicant.application.port.out.UpdateApplicantPort;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class UpdateApplicantService implements UpdateApplicantUseCase {

	private final UpdateApplicantPort updateApplicantPort;

	@Override
	public void increaseLikeCount(final long applicantId) {
		updateApplicantPort.increaseLikeCount(applicantId);
	}

}
