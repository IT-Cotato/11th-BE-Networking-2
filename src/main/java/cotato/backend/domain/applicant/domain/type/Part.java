package cotato.backend.domain.applicant.domain.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Part {
	PM("기획"),
	DESIGNER("디자이너"),
	FRONTEND("프론트엔드"),
	BACKEND("백엔드");

	private final String partName;

}
