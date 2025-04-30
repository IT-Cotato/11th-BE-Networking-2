package cotato.backend.domain.example.application;

import cotato.backend.common.exception.EntityNotFoundException;
import cotato.backend.common.exception.ErrorCode;
import cotato.backend.domain.example.dao.ApplicantRepository;
import cotato.backend.domain.example.dto.request.ApplicantRequest;
import cotato.backend.domain.example.entity.Applicant;
import lombok.RequiredArgsConstructor;
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
}
