package cotato.backend.api.controller;

import cotato.backend.api.dto.response.DefaultIdResponse;
import cotato.backend.common.dto.DataResponse;
import cotato.backend.domain.example.application.ApplicantService;
import cotato.backend.domain.example.dto.request.ApplicantRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/applicant")
public class ApplicantController {

    private final ApplicantService applicantService;

    @PostMapping
    public ResponseEntity<DataResponse<DefaultIdResponse>> save(@RequestBody ApplicantRequest request) {
        return ResponseEntity.ok(
                DataResponse.created(
                        DefaultIdResponse.of(applicantService.save(request))
                )
        );
    }




}
