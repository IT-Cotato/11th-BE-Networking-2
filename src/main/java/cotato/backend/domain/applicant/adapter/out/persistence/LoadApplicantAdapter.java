package cotato.backend.domain.applicant.adapter.out.persistence;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import cotato.backend.domain.applicant.adapter.out.persistence.entity.ApplicantJpaEntity;
import cotato.backend.domain.applicant.adapter.out.persistence.mapper.ApplicantMapper;
import cotato.backend.domain.applicant.adapter.out.persistence.repository.ApplicantRepository;
import cotato.backend.domain.applicant.application.port.out.LoadApplicantPort;
import cotato.backend.domain.applicant.domain.model.Applicant;
import cotato.backend.domain.applicant.domain.type.ApplicantSortType;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LoadApplicantAdapter implements LoadApplicantPort {

	private final ApplicantRepository applicantRepository;
	private final ApplicantMapper mapper;

	private static final int DEFAULT_SIZE = 10;

	@Override
	public Optional<Applicant> findById(final long id) {
		return applicantRepository.findById(id).map(mapper::toDomain);
	}

	@Override
    public Page<Applicant> findAll(final int page, final ApplicantSortType sortType) {

		Pageable pageable = PageRequest.of(page, DEFAULT_SIZE, sortType.getSort());
        Page<ApplicantJpaEntity> entities = applicantRepository.findAll(pageable);

        return entities.map(mapper::toDomain);
    }
}
