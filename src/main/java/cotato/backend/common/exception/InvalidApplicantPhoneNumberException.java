package cotato.backend.common.exception;

public class InvalidApplicantPhoneNumberException extends AppException {
    public InvalidApplicantPhoneNumberException(ErrorCode errorCode) {
        super(errorCode);
    }
}