package cotato.backend.common.dto;

public record SuccessResponse<T>(int status, String message, T data) {
}
