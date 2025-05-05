package cotato.backend.common.exception;

public class AdminNotFoundException extends AppException {
    public AdminNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
