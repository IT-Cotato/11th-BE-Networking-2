package cotato.backend.domain.application;

import cotato.backend.domain.applicant.dto.ApplicantDto;
import cotato.backend.domain.application.dto.ApplicationDto;
import cotato.backend.domain.application.dto.ApplicationPagedResponse;
import cotato.backend.domain.application.dto.ApplicationResponse;

public interface ApplicationService {
    public Long save(ApplicantDto applicantDto, ApplicationDto applicationDto);

    public ApplicationResponse getApplicationById(Long applicationId);

    public int addLike(Long applicationId);

    ApplicationPagedResponse getApplications(int page, int size, String sortBy);
}
