package cotato.backend.common.exception;

public class InvalidGrowthException extends AppException {
    public InvalidGrowthException(ErrorCode errorCode) {
        super(errorCode);
    }
}
