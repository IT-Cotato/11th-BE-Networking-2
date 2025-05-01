package cotato.backend.api.controller;

import cotato.backend.domain.applicant.dto.ApplicantRequest;
import cotato.backend.domain.applicant.dto.ApplicantResponse;
import cotato.backend.domain.applicant.application.ApplicantService;
import cotato.backend.api.dto.response.DefaultIdResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/applicants")
@RequiredArgsConstructor
public class ApplicantController {

    private final ApplicantService applicantService;

    @PostMapping
    public ResponseEntity<DefaultIdResponse> createApplicant(@RequestBody ApplicantRequest request) {
        Long id = applicantService.create(request);
        return ResponseEntity.ok(new DefaultIdResponse(id));
    }
}
