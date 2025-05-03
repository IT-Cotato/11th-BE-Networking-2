package cotato.backend.domain.application;

import cotato.backend.domain.applicant.dto.ApplicantDto;
import cotato.backend.domain.application.dto.ApplicationDto;
import cotato.backend.domain.application.dto.ApplicationLikeDetailsResponse;
import cotato.backend.domain.application.dto.ApplicationPagedResponse;
import cotato.backend.domain.application.dto.ApplicationResponse;

public interface ApplicationService {
    public Long save(ApplicantDto applicantDto, ApplicationDto applicationDto);

    public ApplicationResponse getApplicationById(Long applicationId);

    public int getLikeCount(Long applicationId);

    public ApplicationPagedResponse getApplications(int page, int size, String sortBy);

    public ApplicationLikeDetailsResponse getApplicationLikeDetails(Long applicationId);
}
