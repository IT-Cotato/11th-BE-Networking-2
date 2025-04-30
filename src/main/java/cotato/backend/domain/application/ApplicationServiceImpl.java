package cotato.backend.domain.application;

import cotato.backend.domain.applicant.Applicant;
import cotato.backend.domain.applicant.ApplicantService;
import cotato.backend.domain.applicant.dto.ApplicantDto;
import cotato.backend.domain.application.dao.ApplicationRepository;
import cotato.backend.domain.application.dto.ApplicationDto;
import cotato.backend.domain.application.dto.ApplicationResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService{
    private final ApplicantService applicantService;
    private final ApplicationRepository applicationRepository;

    @Override
    @Transactional
    public Long save(ApplicantDto applicantRequest, ApplicationDto applicationRequest) {
        Applicant applicant = applicantService.save(applicantRequest);

        Application application = applicationRequest.toEntity(applicant);
        return applicationRepository.save(application).getId();
    }

    @Override
    @Transactional
    public ApplicationResponse getApplicationById(Long applicationId)  {
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new IllegalArgumentException("지원서를 찾을 수 없습니다."));


        return ApplicationResponse.from(application);
    }

    @Override
    @Transactional
    public int addLike(Long applicationId) {
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new IllegalArgumentException("지원서를 찾을 수 없습니다."));
        return application.addLike();
    }
}
