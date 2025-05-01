package cotato.backend.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
public class BaseResponse<T> {
    private final int status;
    private final String message;
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private final T data;

    public static <T> BaseResponse<?> of(SuccessDetail successDetail) {
        return BaseResponse.builder()
                .status(successDetail.status())
                .message(successDetail.message())
                .build();
    }

    public static <T> BaseResponse<?> of(SuccessDetail successDetail, T data) {
        return BaseResponse.builder()
                .status(successDetail.status())
                .message(successDetail.message())
                .data(data)
                .build();
    }

    public static <T> BaseResponse<?> of(FailureDetail failureDetail) {
        return BaseResponse.builder()
                .status(failureDetail.status())
                .message(failureDetail.message())
                .build();
    }

    public static <T> BaseResponse<?> of(HttpStatus status, String message) {
        return BaseResponse.builder()
                .status(status.value())
                .message(message)
                .build();
    }
}
