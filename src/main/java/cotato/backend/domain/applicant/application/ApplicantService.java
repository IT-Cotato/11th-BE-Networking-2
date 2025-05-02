package cotato.backend.domain.applicant.application;

import cotato.backend.domain.applicant.dto.ApplicantListResponse;
import cotato.backend.domain.applicant.dto.ApplicantResponse;
import cotato.backend.domain.applicant.dto.ApplicantSearchCondition;
import cotato.backend.domain.applicant.entity.Applicant;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cotato.backend.common.exception.EntityNotFoundException;
import cotato.backend.common.exception.ErrorCode;
import cotato.backend.domain.applicant.dao.ApplicantRepository;

import cotato.backend.domain.applicant.dto.ApplicantRequest;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ApplicantService {

    private final ApplicantRepository applicantRepository;

    public Applicant save(Applicant applicant) {
        return applicantRepository.save(applicant);
    }

    public Optional<Applicant> findById(Long id) {
        return applicantRepository.findById(id);
    }

    public void like(Long id) {
        Applicant applicant = applicantRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("지원자를 찾을 수 없습니다."));
        applicant.addLike();
    }
    public Long create(ApplicantRequest request) {
        Applicant applicant = Applicant.builder()
                .name(request.name())
                .generation(request.generation())
                .age(request.age())
                .part(request.part())
                .participation(request.participation())
                .growth(request.growth())
                .phone(request.phone())
                .submittedAt(LocalDateTime.now())
                .likes(0)
                .build();

        applicantRepository.save(applicant);
        return applicant.getId();
    }
    public void addLike(Long id) {
        Applicant applicant = applicantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.ENTITY_NOT_FOUND));


        applicant.addLike(); // likes += 1
        applicantRepository.save(applicant);
    }

    public Page<ApplicantListResponse> getApplicants(ApplicantSearchCondition condition, Pageable pageable) {
        Page<Applicant> applicants = switch (condition) {
            case OLDEST -> applicantRepository.findAllByOrderBySubmittedAtAsc(pageable);
            case LATEST -> applicantRepository.findAllByOrderBySubmittedAtDesc(pageable);
            case MOST_LIKES -> applicantRepository.findAllByOrderByLikesDescSubmittedAtAsc(pageable);
            case HIGH_PARTICIPATION -> applicantRepository.findAllByOrderByParticipationDescSubmittedAtAsc(pageable);
            case HIGH_GROWTH -> applicantRepository.findAllByOrderByGrowthDescSubmittedAtAsc(pageable);
        };

        return applicants.map(ApplicantListResponse::from);
    }
    public ApplicantResponse getApplicant(Long id) {
        Applicant applicant = applicantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.ENTITY_NOT_FOUND));
        return ApplicantResponse.from(applicant);
    }




}
