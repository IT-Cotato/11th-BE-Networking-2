package cotato.backend.domain.example.dto.response;

import java.time.LocalDateTime;

import cotato.backend.domain.example.entity.Applicant;
import cotato.backend.domain.example.entity.Enum.Part;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApplicantSimpleResponse {

    private Long id;
    private Integer generation;
    private Part part;
    private Integer passionScore;
    private Integer growthScore;
    private Integer likeCount;
    private LocalDateTime submittedAt;

    public static ApplicantSimpleResponse from(Applicant applicant) {
        return new ApplicantSimpleResponse(
            applicant.getId(),
            applicant.getGeneration(),
            applicant.getPart(),
            applicant.getPassionScore(),
            applicant.getGrowthScore(),
            applicant.getLikeCount(),
            applicant.getSubmittedAt()
        );
    }
}
