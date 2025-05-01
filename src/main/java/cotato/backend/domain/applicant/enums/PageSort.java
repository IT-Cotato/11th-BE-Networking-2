package cotato.backend.domain.applicant.enums;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public enum PageSort {
	OLD_SUBMISSION_TIME("오래된 제출순",
		Sort.by("createdAt").ascending()),

	RECENT_SUBMISSION_TIME("최신 제출순",
		Sort.by("createdAt").descending()),

	HIGH_LIKES("좋아요 높은순",
		Sort.by("likes").descending().and(Sort.by("createdAt").ascending())),

	HIGH_ACTIVITY("참여 적극성 높은순",
		Sort.by("activity").descending().and(Sort.by("createdAt").ascending())),

	HIGH_GROWTH("성장 의지 높은순",
		Sort.by("growth").descending().and(Sort.by("createdAt").ascending()));

	private final String description;
	private final Sort sort;

	public static Pageable getPageable(int page, int size, PageSort pageSort) {
		return PageRequest.of(Math.max(0, page - 1), size, pageSort.getSort());
	}

	public static PageSort fromString(String sortByString) {
		return switch (sortByString) {
			case "RECENT" -> RECENT_SUBMISSION_TIME;
			case "HIGH_LIKES" -> HIGH_LIKES;
			case "HIGH_ACTIVITY" -> HIGH_ACTIVITY;
			case "HIGH_GROWTH" -> HIGH_GROWTH;
			default -> OLD_SUBMISSION_TIME;
		};
	}
}

