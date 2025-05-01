package cotato.backend.common.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import cotato.backend.common.exception.ErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;

@Getter
public class ErrorResponse extends BaseResponse {

	private final String code;
	private final String message;
	private final String method;
	private final String requestURI;
	private final List<FieldErrorDetail> errors;

	private ErrorResponse(String code, String message, String method, String requestURI, HttpStatus httpStatus) {
		super(httpStatus);
		this.code = code;
		this.message = message;
		this.method = method;
		this.requestURI = requestURI;
		this.errors = new ArrayList<>();
	}

	public static ErrorResponse of(ErrorCode errorCode, HttpServletRequest request) {
		return new ErrorResponse(
			errorCode.getCode(),
			errorCode.getMessage(),
			request.getMethod(),
			request.getRequestURI(),
			errorCode.getHttpStatus()
		);
	}

	public void addValidationErrors(BindingResult bindingResult) {
		bindingResult.getFieldErrors().forEach(this::addValidationError);
	}

	private void addValidationError(FieldError fieldError) {
		this.errors.add(new FieldErrorDetail(
			fieldError.getField(),
			fieldError.getDefaultMessage()
		));
	}

	@Getter
	public static class FieldErrorDetail {
		private final String field;
		private final String message;

		public FieldErrorDetail(String field, String message) {
			this.field = field;
			this.message = message;
		}
	}
}