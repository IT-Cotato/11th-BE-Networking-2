package cotato.backend.domain.applicant.application.port.out;

import java.util.Optional;

import org.springframework.data.domain.Page;

import cotato.backend.domain.applicant.domain.model.Applicant;
import cotato.backend.domain.applicant.domain.type.ApplicantSortType;

public interface LoadApplicantPort {

	Optional<Applicant> findById(final long id);

	Page<Applicant> findAll(final int page, final ApplicantSortType sortType);
}
