package cotato.backend.domain.example.dto.response;

import java.time.LocalDateTime;

import cotato.backend.domain.example.entity.Applicant;
import cotato.backend.domain.example.entity.Enum.Part;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApplicantDetailResponse {

    private Long id;
    private Integer generation;
    private Part part;
    private Integer passionScore;
    private Integer growthScore;
    private Integer likeCount;
    private String phone;
    private LocalDateTime submittedAt;

    public static ApplicantDetailResponse from(Applicant applicant) {
        return new ApplicantDetailResponse(
            applicant.getId(),
            applicant.getGeneration(),
            applicant.getPart(),
            applicant.getPassionScore(),
            applicant.getGrowthScore(),
            applicant.getLikeCount(),
            applicant.getPhone(),
            applicant.getSubmittedAt()
        );
    }
}