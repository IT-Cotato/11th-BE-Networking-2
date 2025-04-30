package cotato.backend.domain.recruitment.api.controller;

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
import cotato.backend.domain.recruitment.api.dto.SubmissionSummary;
import cotato.backend.domain.recruitment.api.dto.request.ApplyRequest;
import cotato.backend.domain.recruitment.api.dto.SubmissionLikeResult;
import cotato.backend.domain.recruitment.api.dto.response.LikeResponse;
import cotato.backend.domain.recruitment.application.RecruitmentService;
import cotato.backend.domain.recruitment.enums.SortType;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/submissions")
public class RecruitmentController {

	private final RecruitmentService recruitmentService;

	@PostMapping("")
	public ResponseEntity<DataResponse<DefaultIdResponse>> apply(
		@RequestBody ApplyRequest request
	) {
		Long submissionId = recruitmentService.apply(
			request.name(),
			request.generation(),
			request.age(),
			request.part(),
			request.participationScore(),
			request.growthMotivation(),
			request.phoneNumber()
		);

		DefaultIdResponse response = DefaultIdResponse.of(submissionId);
		return ResponseEntity.ok(DataResponse.created(response));
	}

	@PatchMapping("/{submissionId}/like")
	public ResponseEntity<DataResponse<LikeResponse>> addLike(@PathVariable Long submissionId) {
		SubmissionLikeResult result = recruitmentService.addLike(submissionId);
		LikeResponse response = LikeResponse.from(result);
		return ResponseEntity.ok(DataResponse.from(response));
	}

	@GetMapping("")
	public ResponseEntity<DataResponse<PageResponse<SubmissionSummary>>> getSubmissions(
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "10") int size,
		@RequestParam(defaultValue = "OLD") SortType sortType
	) {
		Page<SubmissionSummary> summaryPage = recruitmentService.getSubmissions(page, size, sortType);
		PageResponse<SubmissionSummary> response = PageResponse.of(summaryPage, sortType);
		return ResponseEntity.ok(DataResponse.from(response));
	}

	@G
}
