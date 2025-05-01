package cotato.backend.domain.applicant.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public enum Constants {
	DEFAULT_LIKES(0);

	private final int value;
}