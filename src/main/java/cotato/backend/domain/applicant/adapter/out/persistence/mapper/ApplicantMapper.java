package cotato.backend.domain.applicant.adapter.out.persistence.mapper;

import org.springframework.stereotype.Component;

import cotato.backend.domain.applicant.adapter.out.persistence.entity.ApplicantJpaEntity;
import cotato.backend.domain.applicant.domain.model.Applicant;


@Component
public class ApplicantMapper {

	public ApplicantJpaEntity toJpaEntity(final Applicant applicant) {
		return ApplicantJpaEntity.from(applicant);
	}

	public Applicant toDomain(final ApplicantJpaEntity jpaEntity) {
		return Applicant.withId(
			jpaEntity.getId(),
			jpaEntity.getName(),
			jpaEntity.getGeneration(),
			jpaEntity.getAge(),
			jpaEntity.getPart(),
			jpaEntity.getPassion(),
			jpaEntity.getGrowth(),
			jpaEntity.getPhone(),
			jpaEntity.getSubmittedAt(),
			jpaEntity.getLikes()
		);
	}
}
