package cotato.backend.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import cotato.backend.common.dto.ErrorResponse;
import java.time.LocalDateTime;


@RestControllerAdvice
@Slf4j
public class ExceptionController {
    @ExceptionHandler(ApplicationNotFoundException.class)
    public ResponseEntity<ErrorResponse> applicationNotFoundExceptionHandler(ApplicationNotFoundException e) {
        log.error("ApplicationNotFoundException: {}", e.getMessage());
        ErrorResponse response = ErrorResponse.of(
                HttpStatus.NOT_FOUND.value(),
                e.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(AdminNotFoundException.class)
    public ResponseEntity<ErrorResponse> adminNotFoundExceptionHandler(AdminNotFoundException e) {
        log.error("AdminNotFoundException: {}", e.getMessage());
        ErrorResponse response = ErrorResponse.of(
                HttpStatus.NOT_FOUND.value(),
                e.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(LikeAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> likeAlreadyExistsExceptionHandler(LikeAlreadyExistsException e) {
        log.error("LikeAlreadyExistsException: {}", e.getMessage());
        ErrorResponse response = ErrorResponse.of(
                HttpStatus.CONFLICT.value(),
                e.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }


    @ExceptionHandler(InvalidSortOptionException.class)
    public ResponseEntity<ErrorResponse> invalidSortOptionExceptionHandler(InvalidSortOptionException e) {
        log.error("InvalidSortOptionException: {}", e.getMessage());
        ErrorResponse response = ErrorResponse.of(
                HttpStatus.BAD_REQUEST.value(),
                e.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler({
            InvalidApplicantNameException.class,
            InvalidApplicantAgeException.class,
            InvalidApplicantPhoneNumberException.class,
            InvalidGenerationException.class,
            InvalidParticipationException.class,
            InvalidGrowthException.class
    })
    public ResponseEntity<ErrorResponse> handleValidationExceptions(IllegalArgumentException e) {
        log.error("Validation error: {}", e.getMessage());
        ErrorResponse response = ErrorResponse.of(
                HttpStatus.BAD_REQUEST.value(),
                e.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(AdminAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> adminAlreadyExistsExceptionHandler(AdminAlreadyExistsException e) {
        log.error("AdminAlreadyExistsException: {}", e.getMessage());
        ErrorResponse response = ErrorResponse.of(
                HttpStatus.CONFLICT.value(),
                e.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAllExceptions(Exception e) {
        log.error("System Error: ", e);
        ErrorResponse response = ErrorResponse.of(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "서버 내부 오류가 발생했습니다.",
                LocalDateTime.now()
        );
        return ResponseEntity.internalServerError().body(response);
    }
}