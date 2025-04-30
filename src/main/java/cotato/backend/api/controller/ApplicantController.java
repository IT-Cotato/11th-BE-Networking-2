package cotato.backend.api.controller;

import cotato.backend.api.dto.response.DefaultIdResponse;
import cotato.backend.common.dto.DataResponse;
import cotato.backend.domain.applicant.ApplicantSortType;
import cotato.backend.domain.applicant.application.ApplicantService;
import cotato.backend.domain.applicant.dto.request.ApplicantRequest;
import cotato.backend.domain.applicant.dto.response.ApplicantListResponse;
import cotato.backend.domain.applicant.dto.response.ApplicantResponse;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@RequestMapping("/api/applicants")
public class ApplicantController {

    private final ApplicantService applicantService;

    @PostMapping
    public ResponseEntity<DataResponse<DefaultIdResponse>> save(@RequestBody @Valid ApplicantRequest request) {
        return ResponseEntity.ok(
                DataResponse.created(
                        DefaultIdResponse.of(applicantService.save(request))
                )
        );
    }

    @PostMapping("/{id}/like")
    public ResponseEntity<DataResponse<Void>> like(@PathVariable Long id) {
        applicantService.like(id);
        return ResponseEntity.ok(DataResponse.ok());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResponse<ApplicantResponse>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(
                DataResponse.from(applicantService.findById(id))
        );
    }

    @GetMapping
    public ResponseEntity<DataResponse<Page<ApplicantListResponse>>> getApplicants(
            @RequestParam(defaultValue = "OLDEST") ApplicantSortType sortType,
            Pageable pageable
    ) {
        Page<ApplicantListResponse> response = applicantService.getApplicants(sortType, pageable);
        return ResponseEntity.ok(DataResponse.from(response));
    }
}
