package cotato.backend.domain.applicant.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import cotato.backend.domain.applicant.application.port.ApplicantRepository;
import cotato.backend.domain.applicant.domain.Applicant;
import cotato.backend.domain.applicant.entity.ApplicantEntity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ApplicantRepositoryImpl implements ApplicantRepository {

	private final ApplicantJpaRepository applicantJpaRepository;

	@Override
	public Long save(Applicant applicant) {
		return applicantJpaRepository.save(ApplicantEntity.fromDomain(applicant)).getId();
	}

	@Override
	public Optional<Applicant> findById(Long id) {
		return applicantJpaRepository.findById(id)
			.map(ApplicantEntity::toDomain);
	}

	@Override
	public Page<Applicant> findAll(Pageable pageable) {
		return applicantJpaRepository.findAll(pageable)
			.map(ApplicantEntity::toDomain);
	}

	@Override
	public List<Applicant> findByIdIn(List<Long> mostLikedIds) {
		return applicantJpaRepository.findByIdIn(mostLikedIds)
			.stream()
			.map(ApplicantEntity::toDomain)
			.toList();
	}

	@Override
	public Long count() {
		return applicantJpaRepository.count();
	}
}