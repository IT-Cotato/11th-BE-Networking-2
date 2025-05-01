package cotato.backend.domain.applicant.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public enum Part {
	FE("프론트엔드"),
	BE("백엔드"),
	DE("디자인"),
	PM("기획");

	private final String name;
}
