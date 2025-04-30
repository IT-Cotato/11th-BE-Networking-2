package cotato.backend.domain.applicant.dto.request;

import cotato.backend.domain.applicant.entity.Part;
import cotato.backend.domain.applicant.entity.ApplicantEntity;
import jakarta.validation.constraints.*;

import lombok.Getter;


//지원서 제출 시 사용되는 요청 객체
@Getter
public class ApplicantSaveRequest {

    @NotBlank
    private String name;

    @NotNull
    private Integer generation;

    @NotNull
    @Min(22) @Max(30)
    private Integer age;

    @NotNull
    private Part part;

    @NotNull
    @Min(0) @Max(10)
    private Integer participationScore;

    @NotNull
    @Min(0) @Max(10)
    private Integer growthScore;

    @NotNull
    @Pattern(regexp = "^010-\\d{4}-\\d{4}$", message = "전화번호 형식은 010-XXXX-XXXX입니다.")
    private String phoneNumber;

    // Entity로 변환하는 메서드
    public ApplicantEntity toEntity() {
        return ApplicantEntity.builder()
                .name(name)
                .generation(generation)
                .age(age)
                .part(part)
                .participationScore(participationScore)
                .growthScore(growthScore)
                .phoneNumber(phoneNumber)
                .build();
    }
}
