package cotato.backend.common.exception;

public class EntityNotFoundException extends AppException {

	public EntityNotFoundException(ErrorCode errorCode) {
		super(errorCode);
	}
	public EntityNotFoundException(ErrorCode errorCode, String message) {
		super(errorCode, message);
	}
}