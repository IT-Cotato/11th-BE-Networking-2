package cotato.backend.domain.applicant.service;

import cotato.backend.common.exception.EntityNotFoundException;
import cotato.backend.common.exception.ErrorCode;
import cotato.backend.domain.applicant.dto.response.ApplicantListResponse;
import cotato.backend.domain.applicant.dto.response.ApplicantResponse;
import cotato.backend.domain.applicant.entity.ApplicantEntity;
import cotato.backend.domain.applicant.dao.ApplicantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service("applicantQueryService")
@RequiredArgsConstructor
public class ApplicantQueryService {

    private final ApplicantRepository applicantRepository;

    public ApplicantResponse getOne(Long id) {
        ApplicantEntity applicant = applicantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.NOT_FOUND));
        return ApplicantResponse.from(applicant);
    }

    public Page<ApplicantListResponse> getApplicants(String sort, Pageable pageable) {
        Page<ApplicantEntity> page;

        Sort sortOption = switch (sort.toUpperCase()) {
            case "NEWEST" -> Sort.by(Sort.Direction.DESC, "submittedAt");
            case "LIKES" -> Sort.by(Sort.Direction.DESC, "likes").and(Sort.by("submittedAt"));
            case "PARTICIPATION" -> Sort.by(Sort.Direction.DESC, "participationScore").and(Sort.by("submittedAt"));
            case "GROWTH" -> Sort.by(Sort.Direction.DESC, "growthScore").and(Sort.by("submittedAt"));
            default -> Sort.by("submittedAt"); // OLDEST
        };

        Pageable sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sortOption);
        page = applicantRepository.findAll(sortedPageable);

        return page.map(ApplicantListResponse::from);
    }


}
