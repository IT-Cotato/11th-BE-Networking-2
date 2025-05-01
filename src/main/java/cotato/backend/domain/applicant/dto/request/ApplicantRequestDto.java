package cotato.backend.domain.applicant.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import cotato.backend.domain.applicant.enums.Part;
import cotato.backend.domain.applicant.entity.Applicant;

public record ApplicantRequestDto(
	@NotBlank(message = "이름은 필수 입력값입니다")
	@Size(min = 2, max = 10, message = "이름은 2글자 이상 10글자 이하여야 합니다")
	String name,

	@NotNull(message = "지원 기수는 필수 입력값입니다")
	@Min(value = 1, message = "지원 기수는 1 이상이어야 합니다")
	int generation,

	@NotNull(message = "나이는 필수 입력값입니다")
	@Min(value = 22, message = "나이는 22세 이상이어야 합니다")
	@Max(value = 30, message = "나이는 30세 이하이어야 합니다")
	int age,

	@NotNull(message = "파트는 필수 입력값입니다")
	Part part,

	@NotNull(message = "참여 적극성은 필수 입력값입니다")
	@Min(value = 0, message = "참여 적극성은 0 이상이어야 합니다")
	@Max(value = 10, message = "참여 적극성은 10 이하이어야 합니다")
	int activity,

	@NotNull(message = "성장 의지는 필수 입력값입니다")
	@Min(value = 0, message = "성장 의지는 0 이상이어야 합니다")
	@Max(value = 10, message = "성장 의지는 10 이하이어야 합니다")
	int growth,

	@NotNull(message = "전화번호는 필수 입력값입니다")
	@Pattern(regexp = "^010\\d{8}$", message = "전화번호는 010으로 시작하는 11자리 숫자여야 합니다")
	String phoneNumber
) {
	public Applicant toEntity() {
		return Applicant.builder()
			.name(name)
			.generation(generation)
			.age(age)
			.part(part)
			.activity(activity)
			.growth(growth)
			.phoneNumber(phoneNumber)
			.build();
	}
}
