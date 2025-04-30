package cotato.backend.api.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cotato.backend.domain.example.dto.response.PageResponse;
import cotato.backend.domain.example.entity.Enum.SortCondition;
import jakarta.validation.Valid;

import cotato.backend.common.dto.DataResponse;
import cotato.backend.domain.example.dto.request.ApplicantRequest;
import cotato.backend.domain.example.dto.response.ApplicantDetailResponse;
import cotato.backend.domain.example.dto.response.ApplicantSimpleResponse;
import cotato.backend.domain.example.service.ApplicantService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/applicants")
@RequiredArgsConstructor
public class ApplicantController {

    private final ApplicantService applicantService;

    @PostMapping
    public DataResponse<Long> save(@RequestBody @Valid ApplicantRequest request) {
        return DataResponse.created(applicantService.save(request));
    }

    @PostMapping("/{id}/like")
    public DataResponse<Void> addLike(@PathVariable Long id) {
        applicantService.addLike(id);
        return DataResponse.ok();
    }

    @GetMapping
    public DataResponse<PageResponse<ApplicantSimpleResponse>> findAll(Pageable pageable,
        @RequestParam(defaultValue = "OLDEST") SortCondition sort) {
        return DataResponse.from(applicantService.findAll(pageable, sort));
    }

    @GetMapping("/{id}")
    public DataResponse<ApplicantDetailResponse> findById(@PathVariable Long id) {
        return DataResponse.from(applicantService.findById(id));
    }
}