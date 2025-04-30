package cotato.backend.api.controller;

import cotato.backend.api.dto.response.DefaultIdResponse;
import cotato.backend.common.dto.DataResponse;
import cotato.backend.domain.example.application.ApplicantService;
import cotato.backend.domain.example.dto.request.ApplicantRequest;
import cotato.backend.domain.example.dto.response.ApplicantResponse;
import cotato.backend.domain.example.dto.response.PageResponse;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PatchMapping("/{id}/like")
    public ResponseEntity<DataResponse<Void>> increaseLike(@PathVariable Long id) {

        applicantService.increaseLike(id);
        return ResponseEntity.ok(DataResponse.ok());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResponse<ApplicantResponse>> documentDetail(@PathVariable Long id) {

        return ResponseEntity.ok(
                DataResponse.from(
                        applicantService.getDocumentDetail(id)
                )
        );
    }

    @GetMapping("/list")
    public ResponseEntity<DataResponse<PageResponse<ApplicantResponse>>> document(@RequestParam(defaultValue = "0") int page,
                                                                                  @RequestParam(defaultValue = "10") int size,
                                                                                  @RequestParam(defaultValue = "submitTime_asc") String sort) {
        return ResponseEntity.ok(
                DataResponse.from(
                        applicantService.getDocument(page, size, sort)
                )
        );
    }
}
