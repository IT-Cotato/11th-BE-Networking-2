package cotato.backend.domain.application.api.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cotato.backend.common.dto.DataResponse;
import cotato.backend.common.dto.DefaultIdResponse;
import cotato.backend.common.dto.PageResponse;
import cotato.backend.domain.application.api.dto.ApplicationSummary;
import cotato.backend.domain.application.api.dto.request.ApplyRequest;
import cotato.backend.domain.application.api.dto.ApplicationLikeResult;
import cotato.backend.domain.application.api.dto.response.LikeResponse;
import cotato.backend.domain.application.application.ApplicationService;
import cotato.backend.domain.application.enums.SortType;
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

	@GetMapping("")
	public ResponseEntity<DataResponse<PageResponse<ApplicationSummary>>> getApplications(
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "10") int size,
		@RequestParam(defaultValue = "OLD") SortType sortType
	) {
		Page<ApplicationSummary> summaryPage = applicationService.getApplications(page, size, sortType);
		PageResponse<ApplicationSummary> response = PageResponse.of(summaryPage, sortType);
		return ResponseEntity.ok(DataResponse.from(response));
	}
}
