package cotato.backend.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import cotato.backend.common.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * 애플리케이션 전체에서 발생하는 예외를 처리
 * 각 예외 타입별로 @ExceptionHandler 구현
 * 애플리케이션 어디서든 예외가 발생하면 적절한 핸들러 메서드로 라우팅 됨
 */

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	// 처리되지 않은 모든 예외를 잡는 핸들러
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

	@ExceptionHandler(AppException.class)
	public ResponseEntity<ErrorResponse> handleAppCustomException(AppException e, HttpServletRequest request) {
		log.error("AppException 발생: {}", e.getErrorCode().getMessage());
		log.error("에러가 발생한 지점 {}, {}", request.getMethod(), request.getRequestURI());
		ErrorResponse errorResponse = ErrorResponse.of(e.getErrorCode(), request);
		return ResponseEntity
			.status(e.getErrorCode().getHttpStatus())
			.body(errorResponse);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException e, HttpServletRequest request) {
		log.error("HttpMessageNotReadableException 발생: {}", e.getMessage());
		log.error("에러가 발생한 지점 {}, {}", request.getMethod(), request.getRequestURI());

		ErrorCode errorCode = ErrorCode.BAD_REQUEST;

		// Part enum 관련 오류 처리
		if(e.getMessage().contains("cotato.backend.domain.recruitment.enums.Part")) {
			errorCode = ErrorCode.INVALID_PART_VALUE;
		}

		ErrorResponse errorResponse = ErrorResponse.of(errorCode, request);
		return ResponseEntity
			.status(errorCode.getHttpStatus())
			.body(errorResponse);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException (MethodArgumentTypeMismatchException e, HttpServletRequest request) {
		log.error("MethodArgumentNotValidException 발생: {}", e.getMessage());
		log.error("에러가 발생한 지점 {}, {}", request.getMethod(), request.getRequestURI());

		ErrorCode errorCode = ErrorCode.BAD_REQUEST;

		// SortType enum 관련 오류 처리
		if(e.getMessage().contains("cotato.backend.domain.recruitment.enums.SortType")) {
			errorCode = ErrorCode.INVALID_SUBMISSION_SORT_TYPE;
		}

		ErrorResponse errorResponse = ErrorResponse.of(errorCode, request);
		return ResponseEntity
			.status(errorCode.getHttpStatus())
			.body(errorResponse);
	}
}