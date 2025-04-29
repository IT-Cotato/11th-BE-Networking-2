package cotato.backend.domain.applicant.domain.type;

import org.springframework.data.domain.Sort;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ApplicantSortType {

	SUBMITTED_ASC(Sort.by(Sort.Direction.ASC, Field.SUBMITTED_AT)), // 지원 시간 오래된 순
	SUBMITTED_DESC(Sort.by(Sort.Direction.DESC, Field.SUBMITTED_AT)), // 지원 시간 최신 순
	LIKES_DESC(Sort.by(Sort.Direction.DESC, Field.LIKES)
		.and(Sort.by(Sort.Direction.ASC, Field.SUBMITTED_AT))), // 좋아요 높은 순 + 오래된 순
	PASSION_DESC(Sort.by(Sort.Direction.DESC, Field.PASSION)
		.and(Sort.by(Sort.Direction.ASC, Field.SUBMITTED_AT))), // 참여 적극성 높은 순 + 오래된 순
	GROWTH_DESC(Sort.by(Sort.Direction.DESC, Field.GROWTH)
		.and(Sort.by(Sort.Direction.ASC, Field.SUBMITTED_AT))); // 성장 의지 높은 순 + 오래된 순

	private final Sort sort;

	private static class Field {
		static final String SUBMITTED_AT = "submittedAt";
		static final String LIKES = "likes";
		static final String PASSION = "passion";
		static final String GROWTH = "growth";
	}
}
