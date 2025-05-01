package cotato.backend.exception.notFoundInfo;

import cotato.backend.common.FailureDetail;

public class NotFoundFormException extends NotFoundInfoException {
    NotFoundFormException(FailureDetail failureDetail) {
        super(failureDetail);
    }

    public static NotFoundFormException construct() {
        return new NotFoundFormException(FailureDetail.NOT_FOUND_FORM);
    }
}
