package cotato.backend.domain.example.application;

import cotato.backend.common.exception.EntityNotFoundException;
import cotato.backend.common.exception.ErrorCode;
import cotato.backend.domain.example.dao.ApplicantRepository;
import cotato.backend.domain.example.dto.request.ApplicantRequest;
import cotato.backend.domain.example.dto.response.ApplicantResponse;
import cotato.backend.domain.example.dto.response.PageResponse;
import cotato.backend.domain.example.entity.Applicant;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ApplicantService {

    private final ApplicantRepository applicantRepository;

    @Transactional
    public Long save(ApplicantRequest request) {
        Applicant applicant = Applicant.builder()
                .request(request)
                .build();
        return applicantRepository.save(applicant).getId();
    }

    @Transactional
    public void increaseLike(Long id) {

        Applicant applicant = applicantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.NOT_FOUND));

        applicant.increaseLike();
    }

    @Transactional
    public ApplicantResponse getDocumentDetail(Long id) {

        Applicant applicant = applicantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.NOT_FOUND));

        return ApplicantResponse.from(applicant);
    }

    @Transactional
    public PageResponse<ApplicantResponse> getDocument(int page, int size, String sortArg) {
        Sort sort = sortOption(sortArg);
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Applicant> applicantPage = applicantRepository.findAll(pageable);

        return PageResponse.from(applicantPage.map(ApplicantResponse::from), sortArg);
    }

    private Sort sortOption(String sortArg) {
        return switch (sortArg) {
            case "submitTime_desc" -> Sort.by(Sort.Direction.DESC, "submitTime");
            case "likes_desc" -> Sort.by(Sort.Direction.DESC, "likes").and(Sort.by("submitTime"));
            case "passion_desc" -> Sort.by(Sort.Direction.DESC, "passion").and(Sort.by("submitTime"));
            case "growth_desc" -> Sort.by(Sort.Direction.DESC, "growth").and(Sort.by("submitTime"));
            default -> Sort.by("submitTime");
        };
    }

}
