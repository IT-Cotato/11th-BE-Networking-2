package cotato.backend.domain.applicant;

import cotato.backend.domain.applicant.dto.ApplicantDto;

import java.util.List;

public interface ApplicantService {
    public Applicant save(ApplicantDto createApplicantRequest);

    public List<Applicant> getAllApplicants();
}
