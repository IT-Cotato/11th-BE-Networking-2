package cotato.backend.domain.applicant.domain;

import cotato.backend.common.exception.AppException;
import cotato.backend.common.exception.ErrorCode;
import cotato.backend.domain.applicant.enums.SortType;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class SortTypeFactory {

	public static SortType from(String sort) {
		if (sort == null || sort.isEmpty()) {
			return SortType.OLDEST;
		}
		try {
			return SortType.valueOf(sort.toUpperCase());
		} catch (IllegalArgumentException e) {
			throw new AppException(
				ErrorCode.INVALID_SORT_TYPE_EXCEPTION
			);
		}
	}
}