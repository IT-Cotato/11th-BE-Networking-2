package cotato.backend.domain.applicant.application;

import cotato.backend.domain.applicant.dao.ApplicantRepository;
import cotato.backend.domain.applicant.dto.ApplicantRequest;
import cotato.backend.domain.applicant.entity.Applicant;
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

}
