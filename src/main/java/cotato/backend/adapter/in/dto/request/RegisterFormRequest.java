package cotato.backend.adapter.in.dto.request;

import cotato.backend.domain.attribute.Age;
import cotato.backend.domain.attribute.Engagement;
import cotato.backend.domain.attribute.Generation;
import cotato.backend.domain.attribute.GrowthDesire;
import cotato.backend.domain.attribute.Part;
import cotato.backend.domain.attribute.PhoneNumber;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record RegisterFormRequest(
        @NotBlank(message = "이름이 입력되지 않았습니다.")
        @Pattern(regexp = "^[가-힣]{2,10}$", message = "이름은 한글로 2자 이상 10자 이하이어야 합니다.")
        String name,

        @NotNull(message = "지원 기수가 입력되지 않았습니다.")
        @Min(value = Generation.LOWER_BOUND, message = "지원 기수는 1 이상이어야 합니다.")
        Integer generation,

        @NotNull(message = "나이가 입력되지 않았습니다.")
        @Min(value = Age.LOWER_BOUND, message = "나이는 22세 이상 30세 이하이어야 합니다.")
        @Max(value = Age.UPPER_BOUND, message = "나이는 22세 이상 30세 이하이어야 합니다.")
        Integer age,

        @NotNull
        Part part,

        @NotNull(message = "참여 적극성이 입력되지 않았습니다.")
        @Min(value = Engagement.LOWER_BOUND, message = "참여 적극성은 0 이상 10 이하이어야 합니다.")
        @Max(value = Engagement.UPPER_BOUND, message = "참여 적극성은 0 이상 10 이하이어야 합니다.")
        Integer engagement,

        @NotNull(message = "성장 의지가 입력되지 않았습니다.")
        @Min(value = GrowthDesire.LOWER_BOUND, message = "성장 의지는 0 이상 10 이하이어야 합니다.")
        @Max(value = GrowthDesire.UPPER_BOUND, message = "성장 의지는 0 이상 10 이하이어야 합니다.")
        Integer growthDesire,

        @NotBlank(message = "휴대폰 번호가 입력되지 않았습니다.")
        @Pattern(regexp = PhoneNumber.FORMAT_CONSTRAINT, message = "올바르지 않은 휴대폰 번호입니다.")
        String phoneNumber
) {
}
