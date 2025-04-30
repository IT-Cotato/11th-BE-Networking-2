package cotato.backend.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record ErrorResponse(
        int status,
        String message,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
        LocalDateTime timeStamp) {
    public static ErrorResponse of(int status, String message, LocalDateTime timestamp) {
        return new ErrorResponse(status, message, timestamp);
    }
}