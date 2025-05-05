package cotato.backend.domain.applicant;

import cotato.backend.domain.applicant.dao.ApplicantRepository;
import cotato.backend.domain.applicant.dto.ApplicantDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApplicantServiceImpl implements ApplicantService {
    private final ApplicantRepository applicantRepository;
    @Transactional
    public Optional<Applicant> findByPhoneNumber(String phoneNumber) {
        return applicantRepository.findByPhoneNumber(phoneNumber);
    }
    @Override
    public Applicant save(ApplicantDto applicantRequest) {
        // 전화번호로 지원자를 먼저 찾아보고, 이미 있으면 그 지원자를 반환
        // 없으면 새 지원자 엔티티를 만들어 저장한 뒤 반환
        return findByPhoneNumber(applicantRequest.phoneNumber())
                .orElseGet(() -> applicantRepository.save(applicantRequest.toEntity()));
    }

    @Override
    public List<Applicant> getAllApplicants() {
        return applicantRepository.findAll();
    }


}
