package cotato.backend.domain.applicant.application;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cotato.backend.domain.applicant.application.port.ApplicantLikeRepository;
import cotato.backend.domain.applicant.domain.ApplicantLike;
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

	@Transactional
	public Long like(Long applicantId) {
		return applicantLikeRepository.save(ApplicantLike.createNew(applicantId));
	}

	public Page<Long> countMostLikedApplicantIds(Pageable pageable) {
		return applicantLikeRepository.countMostLikedApplicantIds(pageable);
	}
}