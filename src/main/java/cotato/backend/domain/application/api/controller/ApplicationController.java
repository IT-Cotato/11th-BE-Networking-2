package cotato.backend.domain.application.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cotato.backend.common.dto.DataResponse;
import cotato.backend.common.dto.DefaultIdResponse;
import cotato.backend.domain.application.api.dto.request.ApplyRequest;
import cotato.backend.domain.application.api.dto.ApplicationLikeResult;
import cotato.backend.domain.application.api.dto.response.LikeResponse;
import cotato.backend.domain.application.application.ApplicationService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/applications")
public class ApplicationController {

	private final ApplicationService applicationService;

	@PostMapping("")
	public ResponseEntity<DataResponse<DefaultIdResponse>> apply(
		@RequestBody ApplyRequest request
	) {
		Long applicationId = applicationService.apply(
			request.name(),
			request.generation(),
			request.age(),
			request.part(),
			request.participationScore(),
			request.growthMotivation(),
			request.phoneNumber()
		);

		DefaultIdResponse response = DefaultIdResponse.of(applicationId);
		return ResponseEntity.ok(DataResponse.created(response));
	}

	@PatchMapping("/{applicationId}/like")
	public ResponseEntity<DataResponse<LikeResponse>> addLike(@PathVariable Long applicationId) {
		ApplicationLikeResult result = applicationService.addLike(applicationId);
		LikeResponse response = LikeResponse.from(result);
		return ResponseEntity.ok(DataResponse.from(response));
	}
}
