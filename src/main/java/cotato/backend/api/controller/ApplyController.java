package cotato.backend.api.controller;

import cotato.backend.common.dto.ApiResponse;
import cotato.backend.domain.apply.application.ApplyService;
import cotato.backend.domain.apply.dto.request.ApplyCreateRequest;
import cotato.backend.domain.apply.dto.response.ApplyCreateResponse;
import cotato.backend.domain.apply.dto.response.ApplyDetailResponse;
import cotato.backend.domain.apply.dto.response.ApplyPageResponse;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@RequestMapping("/api/apply")
public class ApplyController {

    private final ApplyService applyService;

    // 서류 등록
    @PostMapping
    public ApiResponse<ApplyCreateResponse> createApplicant(@Valid @RequestBody ApplyCreateRequest applyCreateRequest) {
        return ApiResponse.onSuccess(applyService.createApply(applyCreateRequest));
    }

    // 서류 좋아요
    @PostMapping("/{apply_id}/addLike")
    public ApiResponse<String> postApplyLike(@PathVariable("apply_id") Long applyId){
        return ApiResponse.onSuccess(applyService.postApplyLike(applyId));
    }

    // 서류 목록 조회
    @GetMapping("/applies")
    public ApiResponse<ApplyPageResponse> getApplies(
            @RequestParam(defaultValue = "submitTimeAsc") String sort,
            Pageable pageable
    ) {
        return ApiResponse.onSuccess(applyService.getFilteredApplies(sort, pageable));
    }

    // 서류 세부 조회
    @GetMapping("/{apply_id}/detail")
    public ApiResponse<ApplyDetailResponse> getApplyDetail(@PathVariable("apply_id") Long applyId) {
        return ApiResponse.onSuccess(applyService.getApplyDetail(applyId));
    }

}
