package cotato.backend.domain.applicant.adapter.in.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cotato.backend.common.dto.DataResponse;
import cotato.backend.domain.applicant.adapter.in.web.controller.dto.request.ApplicantRequest;
import cotato.backend.domain.applicant.adapter.in.web.controller.dto.response.ApplicantResponse;
import cotato.backend.domain.applicant.application.command.RegisterApplicantCommand;
import cotato.backend.domain.applicant.application.port.in.RegisterApplicantUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/applicant")
public class RegisterApplicantController {

	private final RegisterApplicantUseCase registerApplicantUseCase;

	@PostMapping
	public ResponseEntity<DataResponse<ApplicantResponse>> register(final @Valid @RequestBody ApplicantRequest request) {
		RegisterApplicantCommand command = RegisterApplicantCommand.from(request);
		ApplicantResponse response = registerApplicantUseCase.register(command);
		return ResponseEntity.ok(DataResponse.created(response));
	}

}
