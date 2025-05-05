package cotato.backend.common.exception;

public class InvalidApplicantNameException extends AppException {
    public InvalidApplicantNameException(ErrorCode errorCode) {
        super(errorCode);
    }
}
