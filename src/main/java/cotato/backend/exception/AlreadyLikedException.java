package cotato.backend.exception;

import cotato.backend.common.FailureDetail;

public class AlreadyLikedException extends RuntimeException {
    public final FailureDetail failureDetail;

    AlreadyLikedException(FailureDetail failureDetail) {
        super(failureDetail.message());
        this.failureDetail = failureDetail;
    }

    public static AlreadyLikedException raise() {
        throw new AlreadyLikedException(FailureDetail.ALREADY_LIKED);
    }
}
