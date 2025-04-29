package cotato.backend.domain.applicant.application.port.out;

import cotato.backend.domain.applicant.domain.model.Applicant;

public interface SaveApplicantPort {

	Applicant save(final Applicant applicant);
}
