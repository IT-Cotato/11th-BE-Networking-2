package cotato.backend.domain.applicant.application.port;

import java.util.Optional;

import cotato.backend.domain.applicant.domain.Applicant;

public interface ApplicantRepository {
	Long save(Applicant applicant);

	Optional<Applicant> findById(Long id);
}
