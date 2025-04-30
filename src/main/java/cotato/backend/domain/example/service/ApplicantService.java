package cotato.backend.domain.example.service;

import java.util.Comparator;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cotato.backend.common.exception.EntityNotFoundException;
import cotato.backend.common.exception.ErrorCode;
import cotato.backend.domain.example.dao.ApplicantRepository;
import cotato.backend.domain.example.dto.request.ApplicantRequest;
import cotato.backend.domain.example.dto.response.ApplicantDetailResponse;
import cotato.backend.domain.example.dto.response.ApplicantSimpleResponse;
import cotato.backend.domain.example.dto.response.PageResponse;
import cotato.backend.domain.example.entity.Applicant;

import cotato.backend.domain.example.entity.Enum.SortCondition;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ApplicantService {

    private final ApplicantRepository applicantRepository;

    @Transactional
    public Long save(ApplicantRequest request) {
        Applicant applicant = Applicant.builder()
            .name(request.getName())
            .generation(request.getGeneration())
            .age(request.getAge())
            .part(request.getPart())
            .passionScore(request.getPassionScore())
            .growthScore(request.getGrowthScore())
            .phone(request.getPhone())
            .submittedAt(java.time.LocalDateTime.now())
            .build();

        return applicantRepository.save(applicant).getId();
    }

    @Transactional
    public void addLike(Long id) {
        Applicant applicant = applicantRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(ErrorCode.NOT_FOUND));
        applicant.addLike();
    }

    public ApplicantDetailResponse findById(Long id) {
        return applicantRepository.findById(id)
            .map(ApplicantDetailResponse::from)
            .orElseThrow(() -> new EntityNotFoundException(ErrorCode.NOT_FOUND));
    }

    public PageResponse<ApplicantSimpleResponse> findAll(Pageable pageable, SortCondition sortCondition) {
        List<Applicant> applicants = applicantRepository.findAll();

        Comparator<Applicant> comparator = getComparator(sortCondition);
        applicants.sort(comparator);

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), applicants.size());

        Page<Applicant> page = new PageImpl<>(
            applicants.subList(start, end), pageable, applicants.size());

        Page<ApplicantSimpleResponse> responsePage = page.map(ApplicantSimpleResponse::from);
        return new PageResponse<>(responsePage, sortCondition.name());
    }

    private Comparator<Applicant> getComparator(SortCondition condition) {
        return switch (condition) {
            case NEWEST -> Comparator.comparing(Applicant::getSubmittedAt).reversed();
            case MOST_LIKES -> Comparator.comparing(Applicant::getLikeCount).reversed()
                .thenComparing(Applicant::getSubmittedAt);
            case MOST_PASSION -> Comparator.comparing(Applicant::getPassionScore).reversed()
                .thenComparing(Applicant::getSubmittedAt);
            case MOST_GROWTH -> Comparator.comparing(Applicant::getGrowthScore).reversed()
                .thenComparing(Applicant::getSubmittedAt);
            case OLDEST -> Comparator.comparing(Applicant::getSubmittedAt);
        };
    }
}