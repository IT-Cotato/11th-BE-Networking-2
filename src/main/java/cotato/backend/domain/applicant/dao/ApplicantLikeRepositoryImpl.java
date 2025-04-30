package cotato.backend.domain.applicant.dao;

import org.springframework.stereotype.Repository;

import cotato.backend.domain.applicant.application.port.ApplicantLikeRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ApplicantLikeRepositoryImpl implements ApplicantLikeRepository {

	private final ApplicantLikeJpaRepository applicantLikeJpaRepository;

	@Override
	public Long countByApplicantId(Long applicantId) {
		return applicantLikeJpaRepository.countByApplicantId(applicantId);
	}
}