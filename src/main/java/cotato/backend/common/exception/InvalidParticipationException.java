package cotato.backend.common.exception;

public class InvalidParticipationException extends AppException {
    public InvalidParticipationException(ErrorCode errorCode) {
        super(errorCode);
    }
}
