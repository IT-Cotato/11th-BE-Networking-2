package cotato.backend.common.exception;

public class InvalidGenerationException extends AppException {
    public InvalidGenerationException(ErrorCode errorCode) {
        super(errorCode);
    }
}
