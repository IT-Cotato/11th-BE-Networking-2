package cotato.backend.domain.example.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cotato.backend.common.exception.EntityNotFoundException;
import cotato.backend.common.exception.ErrorCode;
import cotato.backend.domain.example.dao.ApplicantRepository;
import cotato.backend.domain.example.dto.request.ApplicantRequest;
import cotato.backend.domain.example.dto.response.ApplicantDetailResponse;
import cotato.backend.domain.example.dto.response.ApplicantSimpleResponse;
import cotato.backend.domain.example.entity.Applicant;

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

    public Page<ApplicantSimpleResponse> findAll(Pageable pageable) {
        return applicantRepository.findAll(pageable).map(ApplicantSimpleResponse::from);
    }
}