package cotato.backend.common.exception;

public class LikeAlreadyExistsException extends AppException {
    public LikeAlreadyExistsException(ErrorCode errorCode) {
        super(errorCode);
    }
}
