package cotato.backend.common.exception;

public class ApplyArgumentException extends AppException {
    public ApplyArgumentException(ErrorCode errorCode) {
        super(errorCode);
    }

    public ApplyArgumentException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }


}
