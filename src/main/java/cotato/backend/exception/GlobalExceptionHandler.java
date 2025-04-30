package cotato.backend.exception;

import cotato.backend.common.ApiResponse;
import cotato.backend.common.BaseResponse;
import cotato.backend.exception.notFoundInfo.NotFoundInfoException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundInfoException.class)
    public ResponseEntity<BaseResponse<?>> handleNotFoundInfoException(NotFoundInfoException e) {
        return ApiResponse.failure(e.failureDetail);
    }

    @ExceptionHandler(AlreadyLikedException.class)
    public ResponseEntity<BaseResponse<?>> handleAlreadyLikedException(AlreadyLikedException e) {
        return ApiResponse.failure(e.failureDetail);
    }
}