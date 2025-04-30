package cotato.backend.common.exception;

import cotato.backend.common.dto.ErrorResponse;
import cotato.backend.common.dto.ValidationError;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex, HttpServletRequest request) {
		log.warn("Validation 예외 발생: {}", ex.getMessage());

		List<ValidationError> errorDetails = ex.getBindingResult().getFieldErrors().stream()
				.map(error -> new ValidationError(error.getField(), error.getDefaultMessage()))
				.collect(Collectors.toList());

		ErrorResponse errorResponse = ErrorResponse.of(
				HttpStatus.BAD_REQUEST.value(),
				"VALIDATION_FAILED",
				"입력값이 유효하지 않습니다.",
				request.getRequestURI(),
				errorDetails
		);

		return ResponseEntity.badRequest().body(errorResponse);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ErrorResponse> handleTypeMismatch(MethodArgumentTypeMismatchException ex, HttpServletRequest request) {
		log.warn("TypeMismatch 예외 발생: {}", ex.getMessage());

		String message = String.format("'%s' 필드는 %s 형식이어야 합니다.", ex.getName(), ex.getRequiredType().getSimpleName());

		ErrorResponse errorResponse = ErrorResponse.of(
				HttpStatus.BAD_REQUEST.value(),
				"TYPE_MISMATCH",
				message,
				request.getRequestURI()
		);

		return ResponseEntity.badRequest().body(errorResponse);
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<ErrorResponse> handleMissingParam(MissingServletRequestParameterException ex, HttpServletRequest request) {
		log.warn("Missing Parameter 예외 발생: {}", ex.getMessage());

		String message = String.format("필수 파라미터 '%s'이(가) 누락되었습니다.", ex.getParameterName());

		ErrorResponse errorResponse = ErrorResponse.of(
				HttpStatus.BAD_REQUEST.value(),
				"MISSING_PARAMETER",
				message,
				request.getRequestURI()
		);

		return ResponseEntity.badRequest().body(errorResponse);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ErrorResponse> handleInvalidJson(HttpMessageNotReadableException ex, HttpServletRequest request) {
		log.warn("JSON 파싱 예외 발생: {}", ex.getMessage());

		ErrorResponse errorResponse = ErrorResponse.of(
				HttpStatus.BAD_REQUEST.value(),
				"INVALID_JSON",
				"요청 본문의 형식이 잘못되었습니다.",
				request.getRequestURI()
		);

		return ResponseEntity.badRequest().body(errorResponse);
	}

	@ExceptionHandler(AppException.class)
	public ResponseEntity<ErrorResponse> handleAppCustomException(AppException e, HttpServletRequest request) {
		log.error("AppException 발생: {}", e.getErrorCode().getMessage());
		log.error("에러가 발생한 지점 {}, {}", request.getMethod(), request.getRequestURI());

		ErrorResponse errorResponse = ErrorResponse.of(e.getErrorCode(), request);
		return ResponseEntity
				.status(e.getErrorCode().getHttpStatus())
				.body(errorResponse);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleAllException(Exception e, HttpServletRequest request) {
		log.error("처리되지 않은 예외 발생: ", e);
		log.error("에러가 발생한 지점 {}, {}", request.getMethod(), request.getRequestURI());

		ErrorResponse errorResponse = ErrorResponse.of(
				ErrorCode.INTERNAL_SERVER_ERROR,
				request
		);

		return ResponseEntity
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(errorResponse);
	}
}
