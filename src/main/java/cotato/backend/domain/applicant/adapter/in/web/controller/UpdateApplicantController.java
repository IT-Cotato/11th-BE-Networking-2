package cotato.backend.domain.applicant.adapter.in.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cotato.backend.common.dto.DataResponse;
import cotato.backend.domain.applicant.application.port.in.UpdateApplicantUseCase;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/applicant")
public class UpdateApplicantController {

	private final UpdateApplicantUseCase updateApplicantUseCase;

	@PostMapping("/{id}/like")
	public ResponseEntity<DataResponse<Void>> like(final @PathVariable("id") Long id) {

		updateApplicantUseCase.increaseLikeCount(id);
		return ResponseEntity.ok(DataResponse.ok());
	}
}
