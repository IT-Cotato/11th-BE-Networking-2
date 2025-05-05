package cotato.backend.common.exception;

public class InvalidSortOptionException extends AppException {
    public InvalidSortOptionException(ErrorCode errorCode) {
        super(errorCode);
    }
}
