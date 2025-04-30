package cotato.backend.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import cotato.backend.common.exception.ErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse extends BaseResponse {

	private final String code;
	private final String message;
	private final String method;
	private final String requestURI;
	private final List<ValidationError> errors;

	private ErrorResponse(String code, String message, String method, String requestURI,
						  HttpStatus httpStatus, List<ValidationError> errors) {
		super(httpStatus);
		this.code = code;
		this.message = message;
		this.method = method;
		this.requestURI = requestURI;
		this.errors = errors;
	}

	public static ErrorResponse of(ErrorCode errorCode, HttpServletRequest request) {
		return new ErrorResponse(
				errorCode.getCode(),
				errorCode.getMessage(),
				request.getMethod(),
				request.getRequestURI(),
				errorCode.getHttpStatus(),
				null
		);
	}

	public static ErrorResponse of(int status, String code, String message, HttpServletRequest request) {
		return new ErrorResponse(
				code,
				message,
				request.getMethod(),
				request.getRequestURI(),
				HttpStatus.valueOf(status),
				null
		);
	}

	public static ErrorResponse of(int status, String code, String message, String requestURI) {
		return new ErrorResponse(
				code,
				message,
				null,
				requestURI,
				HttpStatus.valueOf(status),
				null
		);
	}

	public static ErrorResponse of(int status, String code, String message, String requestURI, List<ValidationError> errors) {
		return new ErrorResponse(
				code,
				message,
				null,
				requestURI,
				HttpStatus.valueOf(status),
				errors
		);
	}
}
