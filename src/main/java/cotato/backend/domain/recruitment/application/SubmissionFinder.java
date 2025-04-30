package cotato.backend.domain.recruitment.application;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cotato.backend.common.exception.EntityNotFoundException;
import cotato.backend.common.exception.ErrorCode;
import cotato.backend.domain.recruitment.dao.SubmissionRepository;
import cotato.backend.domain.recruitment.entity.SubmissionEntity;
import cotato.backend.domain.recruitment.enums.SortType;
import lombok.RequiredArgsConstructor;

@Component
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SubmissionFinder {

	private final SubmissionRepository submissionRepository;

	public Page<SubmissionEntity> findAll(int page, int size, SortType sortType) {
		Pageable pageable = PageRequest.of(page, size, sortType.toSort());
		return submissionRepository.findAll(pageable);
	}

	public SubmissionEntity findById(Long submissionId) {
		return submissionRepository.findById(submissionId)
			.orElseThrow(() -> new EntityNotFoundException(ErrorCode.SUBMISSION_NOT_FOUND));
	}

}
