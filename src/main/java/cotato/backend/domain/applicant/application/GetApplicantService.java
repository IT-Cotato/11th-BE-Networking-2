package cotato.backend.domain.applicant.application;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cotato.backend.common.exception.ErrorCode;
import cotato.backend.domain.applicant.adapter.in.web.controller.dto.response.ApplicantResponse;
import cotato.backend.domain.applicant.application.port.in.GetApplicantUseCase;
import cotato.backend.domain.applicant.application.port.out.LoadApplicantPort;
import cotato.backend.domain.applicant.domain.model.Applicant;
import cotato.backend.domain.applicant.domain.type.ApplicantSortType;
import cotato.backend.domain.applicant.exception.ApplicantException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetApplicantService implements GetApplicantUseCase {

	private final LoadApplicantPort loadApplicantPort;

	@Override
	public Page<ApplicantResponse> getApplicants(final int page, final ApplicantSortType sortType) {
		return loadApplicantPort.findAll(page, sortType)
			.map(ApplicantResponse::from);
	}

	@Override
	public ApplicantResponse getApplicantDetail(final long id) {
		Applicant applicant = loadApplicantPort.findById(id).orElseThrow(
			() -> new ApplicantException(ErrorCode.APPLICANT_NOT_FOUND)
		);

		return ApplicantResponse.from(applicant);
	}

}
