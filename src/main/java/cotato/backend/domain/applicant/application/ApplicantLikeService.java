package cotato.backend.domain.applicant.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cotato.backend.domain.applicant.application.port.ApplicantLikeRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ApplicantLikeService {

	private final ApplicantLikeRepository applicantLikeRepository;

	public Long getLikes(Long applicantId) {
		return applicantLikeRepository.countByApplicantId(applicantId);
	}
}