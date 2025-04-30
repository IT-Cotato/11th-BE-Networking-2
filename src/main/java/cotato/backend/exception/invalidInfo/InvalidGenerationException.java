package cotato.backend.exception.invalidInfo;

import cotato.backend.common.FailureDetail;

public class InvalidGenerationException extends InvalidInfoException {
    InvalidGenerationException(FailureDetail failureDetail) {
        super(failureDetail);
    }

    public static void raise() {
        throw new InvalidGenerationException(FailureDetail.INVALID_GENERATION);
    }
}
