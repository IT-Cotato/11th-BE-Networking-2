package cotato.backend.exception.invalidInfo;

import cotato.backend.common.FailureDetail;

public class InvalidNameException extends InvalidInfoException {
    InvalidNameException(FailureDetail failureDetail) {
        super(failureDetail);
    }

    public static void raise() {
        throw new InvalidNameException(FailureDetail.INVALID_NAME);
    }
}
