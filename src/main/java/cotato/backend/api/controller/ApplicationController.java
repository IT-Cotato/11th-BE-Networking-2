package cotato.backend.api.controller;

import cotato.backend.domain.applicant.dto.ApplicantDto;
import cotato.backend.domain.application.ApplicationService;
import cotato.backend.domain.application.dto.ApplicationDto;
import cotato.backend.domain.application.dto.ApplicationPagedResponse;
import cotato.backend.domain.application.dto.ApplicationResponse;
import cotato.backend.domain.application.dto.CreateApplicationRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@RequestMapping("/api/v1/applications")
public class ApplicationController {

	private final ApplicationService applicationService;

	@PostMapping
	public Long save(@RequestBody CreateApplicationRequestDto req) {
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
       return applicationService.save(applicantDto, applicationDto);
	}

    @GetMapping("/{id}")
    public ApplicationResponse getApplicationById(@PathVariable("id") Long id) {
        return applicationService.getApplicationById(id);
    }


    @PostMapping("/{id}/like")
    public int addLike(@PathVariable("id") Long applicationId) {
        return applicationService.addLike(applicationId);
    }

    @GetMapping
    public ApplicationPagedResponse getApplications(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "3") int size,
            @RequestParam(name = "sortBy", defaultValue = "submittedAt") String sortBy
    ) {
        return applicationService.getApplications(page, size, sortBy);
    }

}