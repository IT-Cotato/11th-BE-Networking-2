package cotato.backend.domain.applicant.adapter.in.web.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cotato.backend.common.dto.DataResponse;
import cotato.backend.domain.applicant.adapter.in.web.controller.dto.response.ApplicantPreviewListResponse;
import cotato.backend.domain.applicant.adapter.in.web.controller.dto.response.ApplicantResponse;
import cotato.backend.domain.applicant.application.port.in.GetApplicantUseCase;
import cotato.backend.domain.applicant.domain.type.ApplicantSortType;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/applicant")
public class GetApplicantController {

	private final GetApplicantUseCase getApplicantUseCase;

	@GetMapping("/{id}")
	public ResponseEntity<DataResponse<ApplicantResponse>> getApplicantDetail(final @PathVariable("id") Long id) {
		ApplicantResponse response = getApplicantUseCase.getApplicantDetail(id);
		return ResponseEntity.ok(DataResponse.from(response));
	}

	@GetMapping
	public ResponseEntity<DataResponse<ApplicantPreviewListResponse>> getApplicantList(
		final @RequestParam(required = false, defaultValue = "0") @PositiveOrZero int page,
		final @RequestParam(required = false, defaultValue = "SUBMITTED_AT_ASC") ApplicantSortType applicantSortType) {

		Page<ApplicantResponse> responses = getApplicantUseCase.getApplicants(page, applicantSortType);
		ApplicantPreviewListResponse applicantPreviewListResponse = ApplicantPreviewListResponse.from(responses);
		return ResponseEntity.ok(DataResponse.from(applicantPreviewListResponse));
	}
}
