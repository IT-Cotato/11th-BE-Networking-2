package cotato.backend.exception.invalidInfo;

import cotato.backend.common.FailureDetail;

public class InvalidAgeException extends InvalidInfoException {
    InvalidAgeException(FailureDetail failureDetail) {
        super(failureDetail);
    }

    public static void raise() {
        throw new InvalidAgeException(FailureDetail.INVALID_AGE);
    }
}
