package cotato.backend.common.exception;

public class ApplicationNotFoundException extends AppException {
    public ApplicationNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
