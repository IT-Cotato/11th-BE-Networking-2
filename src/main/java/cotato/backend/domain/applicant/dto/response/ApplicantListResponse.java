package cotato.backend.domain.applicant.dto.response;

import cotato.backend.domain.applicant.entity.ApplicantEntity;
import cotato.backend.domain.applicant.entity.Part;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;


@Getter
@Builder
@AllArgsConstructor

//리스트용 요약 정보
public class ApplicantListResponse {

    private final Long id;
    private final String name;
    private final Integer generation;
    private final Part part;
    private final Integer likes;
    private final LocalDateTime submittedAt;
    private final Integer participationScore;
    private final Integer growthScore;


    public static ApplicantListResponse from(ApplicantEntity entity) {
        return ApplicantListResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .generation(entity.getGeneration())
                .part(entity.getPart())
                .likes(entity.getLikes())
                .submittedAt(entity.getSubmittedAt())
                .participationScore(entity.getParticipationScore())
                .growthScore(entity.getGrowthScore())
                .build();
    }
}
