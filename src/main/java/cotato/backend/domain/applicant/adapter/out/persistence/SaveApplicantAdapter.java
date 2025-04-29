package cotato.backend.domain.applicant.adapter.out.persistence;

import org.springframework.stereotype.Component;

import cotato.backend.domain.applicant.adapter.out.persistence.entity.ApplicantJpaEntity;
import cotato.backend.domain.applicant.adapter.out.persistence.mapper.ApplicantMapper;
import cotato.backend.domain.applicant.adapter.out.persistence.repository.ApplicantRepository;
import cotato.backend.domain.applicant.application.port.out.SaveApplicantPort;
import cotato.backend.domain.applicant.domain.model.Applicant;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class SaveApplicantAdapter implements SaveApplicantPort {

	private final ApplicantRepository applicantRepository;
	private final ApplicantMapper applicantMapper;

	@Override
	public Applicant save(final Applicant applicant) {
		ApplicantJpaEntity saved = applicantRepository.save(applicantMapper.toJpaEntity(applicant));
		return applicantMapper.toDomain(saved);
	}
}
