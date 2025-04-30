package cotato.backend.domain.applicant.application.port;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cotato.backend.domain.applicant.domain.Applicant;

public interface ApplicantRepository {
	Long save(Applicant applicant);

	Optional<Applicant> findById(Long id);

	Page<Applicant> findAll(Pageable pageable);

	List<Applicant> findByIdIn(List<Long> mostLikedIds);

	Long count();
}
