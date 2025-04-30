package cotato.backend.api.applicant.controller;

import static org.springframework.http.HttpStatus.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cotato.backend.api.applicant.dto.request.ApplicantRequest;
import cotato.backend.api.applicant.dto.response.ApplicantGetResponse;
import cotato.backend.api.applicant.dto.response.DefaultIdResponse;
import cotato.backend.common.dto.ApiResponse;
import cotato.backend.domain.applicant.application.ApplicantService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/applicants")
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ApplicantController {

	private final ApplicantService applicantService;

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
}
