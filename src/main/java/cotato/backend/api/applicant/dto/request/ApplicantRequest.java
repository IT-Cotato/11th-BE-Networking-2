package cotato.backend.api.applicant.dto.request;

import cotato.backend.domain.applicant.dto.CreateApplicant;
import cotato.backend.domain.applicant.enums.PartType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ApplicantRequest(
	@NotNull(message = "이름은 필수입니다.")
	@Size(min = 2, max = 10, message = "이름은 2글자 이상 10글자 이하여야 합니다.")
	@Pattern(regexp = "^[가-힣]+$", message = "이름은 한글만 입력 가능합니다.")
	String name,

	@NotNull(message = "지원 기수는 필수입니다.")
	@Min(value = 1, message = "지원 기수는 1 이상이어야 합니다.")
	Long generation,

	@NotNull(message = "나이는 필수입니다.")
	@Min(value = 22, message = "나이는 22세 이상이어야 합니다.")
	@Max(value = 30, message = "나이는 30세 이하여야 합니다.")
	Long age,

	@NotNull(message = "파트는 필수입니다.")
	@Pattern(regexp = "^(BE|FE|PM|DE)$", message = "파트는 BE, FE, PM, DE 중 하나여야 합니다.")
	String part,

	@NotNull(message = "참여 적극성은 필수입니다.")
	@Min(value = 0, message = "참여 적극성은 0 이상이어야 합니다.")
	@Max(value = 10, message = "참여 적극성은 10 이하여야 합니다.")
	Long participationLevel,

	@NotNull(message = "성장 의지는 필수입니다.")
	@Min(value = 0, message = "성장 의지는 0 이상이어야 합니다.")
	@Max(value = 10, message = "성장 의지는 10 이하여야 합니다.")
	Long growthWillingness,

	@NotNull(message = "휴대폰 번호는 필수입니다.")
	@Pattern(regexp = "^010\\d{8}$", message = "휴대폰 번호는 010으로 시작하는 11자리 숫자여야 합니다.")
	String phoneNumber
) {

	public CreateApplicant toCreateApplicant() {
		return new CreateApplicant(
			name,
			generation,
			age,
			PartType.fromCode(part),
			participationLevel,
			growthWillingness,
			phoneNumber
		);
	}
}