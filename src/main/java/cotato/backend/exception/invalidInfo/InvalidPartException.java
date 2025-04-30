package cotato.backend.exception.invalidInfo;

import cotato.backend.common.FailureDetail;

public class InvalidPartException extends InvalidInfoException {
    InvalidPartException(FailureDetail failureDetail) {
        super(failureDetail);
    }

    public static InvalidPartException construct() {
        return new InvalidPartException(FailureDetail.INVALID_PART);
    }
}
