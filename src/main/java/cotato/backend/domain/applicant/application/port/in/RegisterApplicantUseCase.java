package cotato.backend.domain.applicant.application.port.in;

import cotato.backend.domain.applicant.adapter.in.web.controller.dto.response.ApplicantResponse;
import cotato.backend.domain.applicant.application.command.RegisterApplicantCommand;

public interface RegisterApplicantUseCase {
	 ApplicantResponse register(final RegisterApplicantCommand command);
}
