package cotato.backend.common.exception;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import cotato.backend.common.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

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

	// RequestBody 검증 시 발생하는 예외 처리
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(
		MethodArgumentNotValidException e,
		HttpServletRequest request
	) {
		log.warn("[MethodArgumentNotValidException] {} {} - {}", request.getMethod(), request.getRequestURI(),
			e.getMessage());

		Map<String, String> reasons = new LinkedHashMap<>();

		e.getBindingResult().getFieldErrors()
			.forEach(error -> reasons.put(error.getField(), error.getDefaultMessage()));

		ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.INVALID_PARAMETER, request, reasons);

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException(
		MethodArgumentTypeMismatchException e,
		HttpServletRequest request
	) {
		log.warn("[MethodArgumentTypeMismatchException] {} {} - {}", request.getMethod(), request.getRequestURI(),
			e.getMessage());

		Map<String, String> reasons = new LinkedHashMap<>();
		reasons.put(e.getName(),
			"잘못된 타입 입력입니다. (" + Objects.requireNonNull(e.getRequiredType()).getSimpleName() + " 타입이어야 합니다)");

		ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.INVALID_PARAMETER, request, reasons);

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(
		HttpMessageNotReadableException e,
		HttpServletRequest request
	) {
		log.warn("[HttpMessageNotReadableException] {} {} - {}", request.getMethod(), request.getRequestURI(),
			e.getMessage());

		Map<String, String> reasons = new LinkedHashMap<>();
		reasons.put("requestBody", "잘못된 형식의 요청입니다.");

		ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.INVALID_PARAMETER, request, reasons);

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}

	@ExceptionHandler(HandlerMethodValidationException.class)
	public ResponseEntity<ErrorResponse> handleHandlerMethodValidationException(
		HandlerMethodValidationException e,
		HttpServletRequest request
	) {
		log.warn("[HandlerMethodValidationException] {} {} - {}", request.getMethod(), request.getRequestURI(),
			e.getMessage());

		Map<String, String> reasons = new LinkedHashMap<>();
		e.getParameterValidationResults().forEach(result ->
			result.getResolvableErrors().forEach(error ->
				reasons.put(error.getCodes() != null ? error.getCodes()[0] : "parameter", error.getDefaultMessage())
			)
		);

		ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.INVALID_PARAMETER, request, reasons);

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}

}