package cotato.backend.common.exception;

public class InvalidApplicantAgeException extends AppException {
    public InvalidApplicantAgeException(ErrorCode errorCode) {
        super(errorCode);
    }
}

