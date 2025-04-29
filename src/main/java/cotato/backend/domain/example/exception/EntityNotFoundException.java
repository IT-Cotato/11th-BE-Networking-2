package cotato.backend.domain.example.exception;

import cotato.backend.common.exception.AppException;
import cotato.backend.common.exception.ErrorCode;

public class EntityNotFoundException extends AppException {

	public EntityNotFoundException(ErrorCode errorCode) {
		super(errorCode);
	}
}