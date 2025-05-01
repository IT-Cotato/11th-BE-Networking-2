package cotato.backend.exception.invalidInfo;

import cotato.backend.common.FailureDetail;

public class InvalidSortOptionException extends InvalidInfoException {
    InvalidSortOptionException(FailureDetail failureDetail) {
        super(failureDetail);
    }

    public static InvalidSortOptionException construct() {
        return new InvalidSortOptionException(FailureDetail.INVALID_SORT_OPTION);
    }
}
