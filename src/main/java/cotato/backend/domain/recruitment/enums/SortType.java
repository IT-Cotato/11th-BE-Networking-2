package cotato.backend.domain.recruitment.enums;

import org.springframework.data.domain.Sort;

public enum SortType {
	OLD("submissionTime", Sort.Direction.ASC),
	NEW("submissionTime", Sort.Direction.DESC),
	LIKE_DESC("likeCount", Sort.Direction.DESC),
	PARTICIPATION_SCORE_DESC("participationScore", Sort.Direction.DESC),
	GROWTH_MOTIVATION_DESC("growthMotivation", Sort.Direction.DESC);

	private final String property;
	private final Sort.Direction direction;

	SortType(String property, Sort.Direction direction) {
		this.property = property;
		this.direction = direction;
	}

	public Sort toSort() {
		return Sort.by(direction, property);
	}
}
