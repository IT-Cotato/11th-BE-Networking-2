package cotato.backend.exception.invalidInfo;

import cotato.backend.common.FailureDetail;

public class InvalidGrowthDesireException extends InvalidInfoException {
    InvalidGrowthDesireException(FailureDetail failureDetail) {
        super(failureDetail);
    }

    public static void raise() {
        throw new InvalidGrowthDesireException(FailureDetail.INVALID_GROWTH_DESIRE);
    }
}
