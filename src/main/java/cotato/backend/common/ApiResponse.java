package cotato.backend.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ApiResponse {
    public static <T> ResponseEntity<BaseResponse<?>> success(SuccessDetail successDetail) {
        return ResponseEntity.status(successDetail.status()).body(BaseResponse.of(successDetail));
    }

    public static <T> ResponseEntity<BaseResponse<?>> success(SuccessDetail successDetail, T data) {
        return ResponseEntity.status(successDetail.status()).body(BaseResponse.of(successDetail, data));
    }

    public static <T> ResponseEntity<BaseResponse<?>> failure(FailureDetail failureDetail) {
        return ResponseEntity.status(failureDetail.status()).body(BaseResponse.of(failureDetail));
    }

    public static <T> ResponseEntity<BaseResponse<?>> generate(HttpStatus status, String message) {
        return ResponseEntity.status(status).body(BaseResponse.of(status, message));
    }
}
