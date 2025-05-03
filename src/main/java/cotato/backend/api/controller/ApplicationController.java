package cotato.backend.api.controller;

import cotato.backend.common.dto.SuccessResponse;
import cotato.backend.domain.applicant.dto.ApplicantDto;
import cotato.backend.domain.application.ApplicationService;
import cotato.backend.domain.application.dto.*;
import cotato.backend.domain.like.LikeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@RequestMapping("/api/v2/applications")
public class ApplicationController {

	private final ApplicationService applicationService;
    private final LikeService likeService;

	@PostMapping
	public ResponseEntity<SuccessResponse<Long>> save(@RequestBody CreateApplicationRequestDto req) {
        ApplicantDto applicantDto = new ApplicantDto(
                req.name(),
                req.age(),
                req.phoneNumber()
        );
        ApplicationDto applicationDto = new ApplicationDto(
                req.generation(),
                req.part(),
                req.participation(),
                req.growth()
        );
        Long save = applicationService.save(applicantDto, applicationDto);
        return ResponseEntity.ok(new SuccessResponse<>(200,"지원서 저장 성공", save));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponse<ApplicationResponse>> getApplicationById(@PathVariable("id") Long id) {
        ApplicationResponse applicationById = applicationService.getApplicationById(id);
        return ResponseEntity.ok(new SuccessResponse<>(200,"지원서 조회 성공", applicationById));
    }


    @PostMapping("/{applicationId}/like")
    public ResponseEntity<SuccessResponse<Integer>> addLike(@PathVariable("applicationId") Long applicationId, @RequestParam("adminId") Long adminId) {
        likeService.addLike(adminId, applicationId);
        int likeCount = applicationService.getApplicationById(applicationId).like();
        return ResponseEntity.ok(new SuccessResponse<>(200,"지원서 좋아요 성공", likeCount));
    }

    @GetMapping("/{applicationId}/like-details")
    public ResponseEntity<SuccessResponse<ApplicationLikeDetailsResponse>> getApplicationLikeDetails(@PathVariable("applicationId") Long applicationId) {
        ApplicationLikeDetailsResponse applicationLikeDetails = applicationService.getApplicationLikeDetails(applicationId);
        return ResponseEntity.ok(new SuccessResponse<>(200,"지원서 좋아요 상세 정보 조회 성공", applicationLikeDetails));
    }




    @GetMapping
    public ResponseEntity<SuccessResponse<ApplicationPagedResponse>> getApplications(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "3") int size,
            @RequestParam(name = "sortBy", defaultValue = "submittedAt") String sortBy
    ) {
        ApplicationPagedResponse applications = applicationService.getApplications(page, size, sortBy);
        return ResponseEntity.ok(new SuccessResponse<>(200,"지원서 페이징 성공", applications));

    }

}