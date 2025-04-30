package cotato.backend.common.exception;

public class EntityValidationException extends AppException{

	public EntityValidationException(ErrorCode errorCode) {
		super(errorCode);
	}
}
