package cotato.backend.domain.applicant.adapter.in.web.controller.dto.request;

import cotato.backend.domain.applicant.domain.type.Part;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record ApplicantRequest(

	@NotBlank
	String name, // 이름

	@NotNull
	Integer generation, // 지원 기수

	@NotNull
	Integer age, // 나이

	@NotNull
	Part part, // 지원 파트

	@NotNull
	Integer passion, // 참여 적극성

	@NotNull
	Integer growth, // 성장 의지

	@NotBlank
	@Pattern(regexp = "^010\\d{8}$")
	String phone
) {
}