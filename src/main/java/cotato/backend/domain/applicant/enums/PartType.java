package cotato.backend.domain.applicant.enums;

import java.util.Arrays;

import lombok.Getter;

@Getter
public enum PartType {
	BACKEND("BE", "백엔드"),
	FRONTEND("FE", "프론트엔드"),
	PM("PM", "기획"),
	DESIGN("DE", "디자인");

	private final String code;
	private final String description;

	PartType(String code, String description) {
		this.code = code;
		this.description = description;
	}

	public static PartType fromCode(String code) {
		return Arrays.stream(PartType.values())
			.filter(partType -> partType.getCode().equals(code))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("Invalid part code: " + code));
	}
}