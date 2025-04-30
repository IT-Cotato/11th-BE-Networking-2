package cotato.backend.domain.applicant.dto.request;

import cotato.backend.domain.applicant.entity.ApplicantEntity;
import cotato.backend.domain.applicant.entity.Part;
import jakarta.validation.constraints.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ApplicantRequest {


    @NotBlank(message = "이름은 필수입니다.")
    @Pattern(regexp = "^[가-힣]{2,10}$", message = "이름은 한글 2자 이상 10자 이하이어야 합니다.")
    private String name;

    @NotNull(message = "지원 기수는 필수입니다.")
    @Min(value = 1, message = "지원 기수는 1 이상이어야 합니다.")
    private Integer generation;

    @NotNull(message = "나이는 필수입니다.")
    @Min(value = 22, message = "나이는 22살 이상이어야 합니다.")
    @Max(value = 30, message = "나이는 30살 이하여야 합니다.")
    private Integer age;

    @NotNull(message = "파트는 필수입니다.")
    private Part part;

    @NotNull(message = "참여 점수는 필수입니다.")
    @Min(value = 0, message = "참여 점수는 0 이상이어야 합니다.")
    @Max(value = 10, message = "참여 점수는 10 이하여야 합니다.")
    private Integer participationScore;

    @NotNull(message = "성장 점수는 필수입니다.")
    @Min(value = 0, message = "성장 점수는 0 이상이어야 합니다.")
    @Max(value = 10, message = "성장 점수는 10 이하여야 합니다.")
    private Integer growthScore;

    @NotBlank(message = "휴대폰 번호는 필수입니다.")
    @Pattern(regexp = "^010\\d{8}$", message = "휴대폰 번호는 010으로 시작하는 11자리 숫자여야 합니다.")
    private String phoneNumber;

    public ApplicantEntity toEntity() {
        return ApplicantEntity.builder()
                .name(name)
                .generation(generation)
                .age(age)
                .part(part)
                .participationScore(participationScore)
                .growthScore(growthScore)
                .phoneNumber(phoneNumber)
                .submittedAt(LocalDateTime.now())
                .likes(0)  // 기본 좋아요 수는 0
                .build();
    }
}
