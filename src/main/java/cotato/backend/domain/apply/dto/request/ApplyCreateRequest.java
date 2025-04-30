package cotato.backend.domain.apply.dto.request;

import cotato.backend.domain.apply.entity.PartType;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ApplyCreateRequest {

    @Size(min = 2, max = 10, message = "이름은 한글 2자 이상, 10자 이하여야 합니다.")
    private String name;

    @Min(value = 1, message = "지원 기수는 1 이상이어야 합니다.")
    private int generation;

    @Min(value = 22, message = "나이는 22살 이상이어야 합니다.")
    @Max(value = 30, message = "나이는 30살 이하여야 합니다.")
    private int age;

    @NotNull(message = "파트는 필수입니다.")
    private PartType part;

    @Min(value = 0, message = "참여 적극성은 0 이상이어야 합니다.")
    @Max(value = 10, message = "참여 적극성은 10 이하여야 합니다.")
    private int participationScore;

    @Min(value = 0, message = "성장 의지는 0 이상이어야 합니다.")
    @Max(value = 10, message = "성장 의지는 10 이하여야 합니다.")
    private int growthScore;

    @NotBlank(message = "휴대폰 번호는 필수입니다.")
    @Pattern(regexp = "^010\\d{8}$", message = "휴대폰 번호는 010으로 시작하는 11자리여야 합니다.")
    private String phoneNumber;
}
