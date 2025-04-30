package cotato.backend.domain.applicant.enums;

import org.springframework.data.domain.Sort;

public enum SortType {
	OLDEST {
		@Override
		public Sort getSort() {
			return Sort.by("submissionTime").ascending();
		}
	},
	NEWEST {
		@Override
		public Sort getSort() {
			return Sort.by("submissionTime").descending();
		}
	},
	MOST_LIKED {
		@Override
		public Sort getSort() {
			// 이 타입은 특별 처리 필요한 플래그로 사용됨
			return null;
		}
	},
	MOST_ACTIVE {
		@Override
		public Sort getSort() {
			return Sort.by("participationLevel").descending()
				.and(Sort.by("submissionTime").ascending());
		}
	},
	MOST_GROWTH {
		@Override
		public Sort getSort() {
			return Sort.by("growthWillingness").descending()
				.and(Sort.by("submissionTime").ascending());
		}
	};

	public abstract Sort getSort();
}