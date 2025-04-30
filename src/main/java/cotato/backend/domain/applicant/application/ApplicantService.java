package cotato.backend.domain.applicant.application;

import cotato.backend.common.exception.EntityNotFoundException;
import cotato.backend.common.exception.ErrorCode;
import cotato.backend.domain.applicant.dao.ApplicantRepository;
import cotato.backend.domain.applicant.entity.ApplicantEntity;
import cotato.backend.domain.applicant.dto.request.ApplicantRequest;
import cotato.backend.domain.applicant.dto.response.ApplicantResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ApplicantService {

    private final ApplicantRepository applicantRepository;

    @Transactional
    public Long save(ApplicantRequest request) {
        ApplicantEntity applicant = request.toEntity();
        return applicantRepository.save(applicant).getId();
    }

    public ApplicantResponse findById(Long id) {
        return ApplicantResponse.from(
                applicantRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException(ErrorCode.NOT_FOUND))
        );
    }

    public Page<ApplicantResponse> findAll(Pageable pageable) {
        return applicantRepository.findAll(pageable)
                .map(ApplicantResponse::from);
    }

    @Transactional
    public void like(Long id) {
        ApplicantEntity applicant = applicantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.NOT_FOUND));
        applicant.increaseLikes();
    }
}
