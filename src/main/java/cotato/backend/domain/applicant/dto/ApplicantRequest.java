package cotato.backend.domain.applicant.dto;

import cotato.backend.domain.applicant.entity.Applicant.Part;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;


public record ApplicantRequest(
        @NotBlank(message = "이름은 필수입니다.")
        @Size(min = 2, max = 10, message = "이름은 2자 이상 10자 이하여야 합니다.")
        String name,

        @Min(value = 1, message = "지원 기수는 1 이상이어야 합니다.")
        int generation,

        @Min(value = 22, message = "나이는 22세 이상이어야 합니다.")
        @Max(value = 30, message = "나이는 30세 이하여야 합니다.")
        int age,

        @NotNull(message = "파트는 필수입니다.")
        Part part,

        @Min(value = 0) @Max(value = 10)
        int participation,

        @Min(value = 0) @Max(value = 10)
        int growth,

        @Pattern(regexp = "^010\\d{8}$", message = "휴대폰 번호는 010으로 시작하는 11자리여야 합니다.")
        String phone
) {}
