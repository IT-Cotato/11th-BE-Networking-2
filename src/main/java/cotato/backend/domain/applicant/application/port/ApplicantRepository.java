package cotato.backend.domain.applicant.application.port;

import cotato.backend.domain.applicant.domain.Applicant;

public interface ApplicantRepository {
	Long save(Applicant applicant);
}
