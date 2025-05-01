package cotato.backend.exception.notFoundInfo;

import cotato.backend.common.FailureDetail;

public class NotFoundInfoException extends RuntimeException {
    public final FailureDetail failureDetail;

    public NotFoundInfoException(FailureDetail failureDetail) {
        super(failureDetail.message());
        this.failureDetail = failureDetail;
    }
}
