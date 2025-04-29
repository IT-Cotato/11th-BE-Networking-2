package cotato.backend.api.controller;

import cotato.backend.api.dto.response.ApiResponse;
import cotato.backend.domain.apply.application.ApplyService;
import cotato.backend.domain.apply.dto.request.ApplyCreateRequest;
import cotato.backend.domain.apply.dto.response.ApplyCreateResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@RequestMapping("/api/apply")
public class ApplyController {

    private final ApplyService applyService;

    // 서류 등록 기능
    @PostMapping
    public ApiResponse<ApplyCreateResponse> createApplicant(@RequestBody ApplyCreateRequest applyCreateRequest) {
        return ApiResponse.onSuccess(applyService.createApply(applyCreateRequest));
    }

    // 서류 좋아요 기능

    // 서류 목록 조회 기능(페이지, 필터링)

    // 서류 세부 조회 기능
}
