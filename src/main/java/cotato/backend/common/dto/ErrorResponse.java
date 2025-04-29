package cotato.backend.common.dto;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

import cotato.backend.common.exception.ErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse extends BaseResponse {

	private final String code;
	private final String message;
	private final String method;
	private final String requestURI;
	private final Map<String, String> reasons; // 필드 검증 오류 정보 (optional)

	private ErrorResponse(
		String code,
		String message,
		String method,
		String requestURI,
		HttpStatus httpStatus,
		Map<String, String> reasons
	) {
		super(httpStatus);
		this.code = code;
		this.message = message;
		this.method = method;
		this.requestURI = requestURI;
		this.reasons = reasons;
	}

	// 기본 에러 (reasons 없음)
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

	// 필드 검증 실패용 (reasons 포함)
	public static ErrorResponse of(ErrorCode errorCode, HttpServletRequest request, Map<String, String> reasons) {
		return new ErrorResponse(
			errorCode.getCode(),
			errorCode.getMessage(),
			request.getMethod(),
			request.getRequestURI(),
			errorCode.getHttpStatus(),
			reasons
		);
	}
}
