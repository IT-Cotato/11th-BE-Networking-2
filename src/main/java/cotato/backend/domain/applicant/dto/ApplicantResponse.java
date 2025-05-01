package cotato.backend.domain.applicant.dto;

import cotato.backend.domain.applicant.entity.Applicant;
import cotato.backend.domain.applicant.entity.Applicant.Part;

import java.time.LocalDateTime;

public record ApplicantResponse(
        Long id,
        int generation,
        Part part,
        int participation,
        int growth,
        int likes,
        String phone,
        LocalDateTime submittedAt
) {
    public static ApplicantResponse from(Applicant applicant) {
        return new ApplicantResponse(
                applicant.getId(),
                applicant.getGeneration(),
                applicant.getPart(),
                applicant.getParticipation(),
                applicant.getGrowth(),
                applicant.getLikes(),
                applicant.getPhone(),
                applicant.getSubmittedAt()
        );
    }
}
