package cotato.backend.exception.notFoundInfo;

import cotato.backend.common.FailureDetail;

public class NotFoundAdminException extends NotFoundInfoException {
    NotFoundAdminException(FailureDetail failureDetail) {
        super(failureDetail);
    }

    public static NotFoundAdminException construct() {
        return new NotFoundAdminException(FailureDetail.NOT_FOUND_ADMIN);
    }
}
