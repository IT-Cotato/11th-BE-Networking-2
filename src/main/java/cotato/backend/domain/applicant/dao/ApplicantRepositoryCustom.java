package cotato.backend.domain.applicant.dao;

import cotato.backend.domain.applicant.dto.ApplicantListResponse;
import cotato.backend.domain.applicant.dto.ApplicantSearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ApplicantRepositoryCustom {
    Page<ApplicantListResponse> findApplicants(ApplicantSearchCondition condition, Pageable pageable);
}
