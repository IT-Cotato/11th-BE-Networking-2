package cotato.backend.common.exception;

public class AdminAlreadyExistsException extends AppException {
    public AdminAlreadyExistsException(ErrorCode errorCode) {
        super(errorCode);
    }
}
