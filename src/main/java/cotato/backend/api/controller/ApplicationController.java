package cotato.backend.api.controller;

import cotato.backend.domain.applicant.dto.ApplicantDto;
import cotato.backend.domain.application.ApplicationService;
import cotato.backend.domain.application.dto.ApplicationDto;
import cotato.backend.domain.application.dto.ApplicationResponse;
import cotato.backend.domain.application.dto.CreateApplicationRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}