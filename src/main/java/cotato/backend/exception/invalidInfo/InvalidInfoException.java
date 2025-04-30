package cotato.backend.exception.invalidInfo;

import cotato.backend.common.FailureDetail;

public class InvalidInfoException extends RuntimeException {
    public final FailureDetail failureDetail;

    public InvalidInfoException(FailureDetail failureDetail) {
        super(failureDetail.message());
        this.failureDetail = failureDetail;
    }
}
