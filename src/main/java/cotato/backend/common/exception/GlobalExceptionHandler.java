package cotato.backend.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import cotato.backend.common.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * 처리되지 않은 모든 예외를 잡는 핸들러
	 */
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

	/**
	 * {@link AppException} 예외를 핸들링하는 메서드
	 */
	@ExceptionHandler(AppException.class)
	public ResponseEntity<ErrorResponse> handleAppCustomException(AppException e, HttpServletRequest request) {
		log.error("AppException 발생: {}", e.getErrorCode().getMessage());
		log.error("에러가 발생한 지점 {}, {}", request.getMethod(), request.getRequestURI());
		ErrorResponse errorResponse = ErrorResponse.of(e.getErrorCode(), request);
		return ResponseEntity
			.status(e.getErrorCode().getHttpStatus())
			.body(errorResponse);
	}

	/**
	 * {@link MethodArgumentNotValidException} 예외를 핸들링하는 메서드
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
		HttpServletRequest request) {
		log.error("Method Argument Validation Error 발생: {}", ex.getMessage());
		log.error("에러가 발생한 지점 {}, {}", request.getMethod(), request.getRequestURI());

		ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.INVALID_PARAMETER, request);
		errorResponse.addValidationErrors(ex.getBindingResult());

		return ResponseEntity
			.status(HttpStatus.BAD_REQUEST)
			.body(errorResponse);
	}
}