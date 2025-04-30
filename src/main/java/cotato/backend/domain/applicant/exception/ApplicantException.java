package cotato.backend.domain.applicant.exception;

import cotato.backend.common.exception.AppException;
import cotato.backend.common.exception.ErrorCode;

public class ApplicantException extends AppException {
	public ApplicantException(ErrorCode errorCode) {
		super(errorCode);
	}
}
