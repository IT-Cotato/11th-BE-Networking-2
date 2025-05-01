package cotato.backend.exception;

import cotato.backend.common.ApiResponse;
import cotato.backend.common.BaseResponse;
import cotato.backend.exception.invalidInfo.InvalidInfoException;
import cotato.backend.exception.notFoundInfo.NotFoundInfoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(InvalidInfoException.class)
    public ResponseEntity<BaseResponse<?>> handleInvalidInfoException(InvalidInfoException e) {
        return ApiResponse.failure(e.failureDetail);
    }

    @ExceptionHandler(NotFoundInfoException.class)
    public ResponseEntity<BaseResponse<?>> handleNotFoundInfoException(NotFoundInfoException e) {
        return ApiResponse.failure(e.failureDetail);
    }

    @ExceptionHandler(AlreadyLikedException.class)
    public ResponseEntity<BaseResponse<?>> handleAlreadyLikedException(AlreadyLikedException e) {
        return ApiResponse.failure(e.failureDetail);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResponse<?>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return ApiResponse.generate(HttpStatus.BAD_REQUEST, e.getBindingResult().getFieldError().getDefaultMessage());
    }
}