package cotato.backend.domain.applicant.application.port.in;

import org.springframework.data.domain.Page;

import cotato.backend.domain.applicant.adapter.in.web.controller.dto.response.ApplicantResponse;
import cotato.backend.domain.applicant.domain.type.ApplicantSortType;

public interface GetApplicantUseCase {

	Page<ApplicantResponse> getApplicants(final int page, final ApplicantSortType sortType);

	ApplicantResponse getApplicantDetail(final long id);
}
