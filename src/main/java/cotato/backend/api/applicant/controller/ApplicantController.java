package cotato.backend.api.applicant.controller;

import static org.springframework.http.HttpStatus.*;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cotato.backend.api.applicant.dto.request.ApplicantRequest;
import cotato.backend.api.applicant.dto.response.ApplicantGetResponse;
import cotato.backend.api.applicant.dto.response.DefaultIdResponse;
import cotato.backend.common.dto.ApiResponse;
import cotato.backend.common.dto.PageResponse;
import cotato.backend.domain.applicant.application.ApplicantLikeService;
import cotato.backend.domain.applicant.application.ApplicantService;
import cotato.backend.domain.applicant.domain.SortTypeFactory;
import cotato.backend.domain.applicant.enums.SortType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/applicants")
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Validated
public class ApplicantController {

	private final ApplicantService applicantService;
	private final ApplicantLikeService applicantLikeService;

	@PostMapping
	public ResponseEntity<ApiResponse<DefaultIdResponse>> save(@RequestBody @Valid ApplicantRequest request) {
		return ResponseEntity.status(CREATED).body(
			ApiResponse.created(
				DefaultIdResponse.of(applicantService.save(request.toCreateApplicant()))
			)
		);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<ApplicantGetResponse>> get(@PathVariable Long id) {
		return ResponseEntity.ok(
			ApiResponse.ok(
				applicantService.getApplicant(id)
			)
		);
	}

	@GetMapping
	public ResponseEntity<ApiResponse<PageResponse<ApplicantGetResponse>>> getAll(
		@RequestParam(required = false, defaultValue = "0") @Min(value = 0, message = "페이지 번호는 0 이상이어야 합니다") Integer page,
		@RequestParam(required = false, defaultValue = "10")
		@Min(value = 10, message = "페이지 크기는 10 이상이어야 합니다.")
		@Max(value = 20, message = "페이지 크기는 20 이하여야 합니다.") Integer size,
		@RequestParam(required = false, defaultValue = "OLDEST") String sort
	) {
		SortType SortType = SortTypeFactory.from(sort);
		return ResponseEntity.ok(
			ApiResponse.ok(
				applicantService.getApplicants(page, size, SortType)
			)
		);
	}

	@PostMapping("/likes/{id}")
	public ResponseEntity<ApiResponse<DefaultIdResponse>> like(@PathVariable Long id) {
		return ResponseEntity.status(CREATED).body(
			ApiResponse.created(
				DefaultIdResponse.of(applicantLikeService.like(id))
			)
		);
	}
}
