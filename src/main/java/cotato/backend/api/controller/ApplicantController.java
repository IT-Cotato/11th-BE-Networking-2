package cotato.backend.api.controller;

import cotato.backend.common.dto.DataResponse;
import cotato.backend.domain.applicant.dto.ApplicantListResponse;
import cotato.backend.domain.applicant.dto.ApplicantRequest;
import cotato.backend.domain.applicant.dto.ApplicantResponse;
import cotato.backend.domain.applicant.application.ApplicantService;
import cotato.backend.api.dto.response.DefaultIdResponse;
import cotato.backend.domain.applicant.dto.ApplicantSearchCondition;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/applicants")
@RequiredArgsConstructor
public class ApplicantController {

    private final ApplicantService applicantService;
    
    @PostMapping("/{id}/like")
    public ResponseEntity<Void> addLike(@PathVariable Long id) {
        applicantService.addLike(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping
    public ResponseEntity<DataResponse<Page<ApplicantListResponse>>> getApplicants(
            @RequestParam(defaultValue = "OLDEST") ApplicantSearchCondition condition,
            @PageableDefault(size = 10, sort = "submittedAt") Pageable pageable
    ) {
        Page<ApplicantListResponse> result = applicantService.getApplicants(condition, pageable);
        return ResponseEntity.ok(DataResponse.from(result));
    }
    @GetMapping("/{id}")
    public ResponseEntity<DataResponse<ApplicantResponse>> getApplicant(@PathVariable Long id) {
        ApplicantResponse response = applicantService.getApplicant(id);
        return ResponseEntity.ok(DataResponse.from(response));
    }
    @PostMapping
    public ResponseEntity<DefaultIdResponse> createApplicant(@RequestBody @Valid ApplicantRequest request) {
        Long id = applicantService.create(request);
        return ResponseEntity.ok(new DefaultIdResponse(id));
    }




}
