package cotato.backend.domain.application;

import cotato.backend.common.exception.ApplicationNotFoundException;
import cotato.backend.common.exception.InvalidSortOptionException;
import cotato.backend.domain.applicant.Applicant;
import cotato.backend.domain.applicant.ApplicantService;
import cotato.backend.domain.applicant.dto.ApplicantDto;
import cotato.backend.domain.application.dao.ApplicationRepository;
import cotato.backend.domain.application.dto.ApplicationDto;
import cotato.backend.domain.application.dto.ApplicationListResponse;
import cotato.backend.domain.application.dto.ApplicationPagedResponse;
import cotato.backend.domain.application.dto.ApplicationResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


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
                .orElseThrow(() -> new ApplicationNotFoundException());
        return ApplicationResponse.from(application);
    }

    @Override
    @Transactional
    public int getLikeCount(Long applicationId) {
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new ApplicationNotFoundException());
        return application.getLikeCount();
    }

    public ApplicationPagedResponse getApplications(int page, int size, String sortBy) {
        if (!Set.of("submittedAt", "submittedAt_desc", "like", "participation", "growth").contains(sortBy)) {
            throw new InvalidSortOptionException();
        }
        Sort sort = switch (sortBy) {
            // 지원 시간 오래된 순
            case "submittedAt" -> Sort.by(Sort.Direction.ASC, "submittedAt");
            // 지원 시간 최신 순
            case "submittedAt_desc" -> Sort.by(Sort.Direction.DESC, "submittedAt");
            // 좋아요 높은 순 (좋아요 수가 같을 경우 지원시간 오래된 순)
            case "like" -> Sort.by(Sort.Direction.DESC, "like")
                    .and(Sort.by(Sort.Direction.ASC, "submittedAt"));
            // 참여 적극성 높은 순(같은 경우 지원시간 오래된 순)
            case "participation" -> Sort.by(Sort.Direction.DESC, "participation")
                    .and(Sort.by(Sort.Direction.ASC, "submittedAt"));
            // 성장 의지 높은 순(같은 경우 지원시간 오래된 순)
            case "growth" -> Sort.by(Sort.Direction.DESC, "growth")
                    .and(Sort.by(Sort.Direction.ASC, "submittedAt"));
            // 기본값
            default -> Sort.by(Sort.Direction.ASC, "submittedAt");
        };

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Application> applicationPage = applicationRepository.findAll(pageable);

        List<ApplicationListResponse> content = applicationPage.getContent().stream()
                .map(app -> new ApplicationListResponse(
                        app.getApplicant().getId(),
                        app.getId(),
                        app.getGeneration(),
                        app.getPart(),
                        app.getParticipation(),
                        app.getGrowth(),
                        app.getLikeCount(),
                        app.getSubmittedAt()
                )).toList();

        return new ApplicationPagedResponse(
                content,
                applicationPage.getNumber(),
                applicationPage.getSize(),
                sortBy,
                applicationPage.getTotalElements(),
                applicationPage.getTotalPages()

        );
    }
}
