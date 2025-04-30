package cotato.backend.domain.applicant.dto.response;

import cotato.backend.domain.applicant.entity.ApplicantEntity;
import cotato.backend.domain.applicant.entity.Part;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
//개별 지원자 조회 시 반환할 상세 정보용 DTO
public class ApplicantDetailResponse {

    private final Long id;
    private final String name;
    private final Integer generation;
    private final Integer age;
    private final Part part;
    private final Integer participationScore;
    private final Integer growthScore;
    private final String phoneNumber;
    private final LocalDateTime submittedAt;
    private final Integer likes;

    @Builder
    private ApplicantDetailResponse(Long id, String name, Integer generation, Integer age, Part part,
                                    Integer participationScore, Integer growthScore,
                                    String phoneNumber, LocalDateTime submittedAt, Integer likes) {
        this.id = id;
        this.name = name;
        this.generation = generation;
        this.age = age;
        this.part = part;
        this.participationScore = participationScore;
        this.growthScore = growthScore;
        this.phoneNumber = phoneNumber;
        this.submittedAt = submittedAt;
        this.likes = likes;
    }

    public static ApplicantDetailResponse from(ApplicantEntity entity) {
        return ApplicantDetailResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .generation(entity.getGeneration())
                .age(entity.getAge())
                .part(entity.getPart())
                .participationScore(entity.getParticipationScore())
                .growthScore(entity.getGrowthScore())
                .phoneNumber(entity.getPhoneNumber())
                .submittedAt(entity.getSubmittedAt())
                .likes(entity.getLikes())
                .build();
    }
}
