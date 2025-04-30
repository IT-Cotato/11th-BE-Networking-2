package cotato.backend.exception.invalidInfo;

import cotato.backend.common.FailureDetail;

public class InvalidEngagementException extends InvalidInfoException {
    InvalidEngagementException(FailureDetail failureDetail) {
        super(failureDetail);
    }

    public static void raise() {
        throw new InvalidEngagementException(FailureDetail.INVALID_ENGAGEMENT);
    }
}
