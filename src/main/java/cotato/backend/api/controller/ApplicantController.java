package cotato.backend.api.controller;

import cotato.backend.domain.applicant.Applicant;
import cotato.backend.domain.applicant.ApplicantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/applicants")
@RequiredArgsConstructor
public class ApplicantController {
    private final ApplicantService applicantService;
    @GetMapping
    public List<Applicant> getAllApplicants() {
        return applicantService.getAllApplicants();
    }
}
