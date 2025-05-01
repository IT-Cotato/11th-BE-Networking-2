package cotato.backend.exception.invalidInfo;

import cotato.backend.common.FailureDetail;

public class InvalidPhoneNumberException extends InvalidInfoException {
    InvalidPhoneNumberException(FailureDetail failureDetail) {
        super(failureDetail);
    }

    public static void raise() {
        throw new InvalidPhoneNumberException(FailureDetail.INVALID_PHONE_NUMER);
    }
}
