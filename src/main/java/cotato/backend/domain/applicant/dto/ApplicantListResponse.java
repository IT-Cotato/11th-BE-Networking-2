package cotato.backend.domain.applicant.dto;

import cotato.backend.domain.applicant.entity.Applicant;
import cotato.backend.domain.applicant.entity.Applicant.Part;
import java.time.LocalDateTime;

public record ApplicantListResponse(
        Long id,
        int generation,
        Part part,
        int participation,
        int growth,
        int likes,
        LocalDateTime submittedAt
) {
    public static ApplicantListResponse from(Applicant applicant) {
        return new ApplicantListResponse(
                applicant.getId(),
                applicant.getGeneration(),
                applicant.getPart(),
                applicant.getParticipation(),
                applicant.getGrowth(),
                applicant.getLikes(),
                applicant.getSubmittedAt()
        );
    }
}
