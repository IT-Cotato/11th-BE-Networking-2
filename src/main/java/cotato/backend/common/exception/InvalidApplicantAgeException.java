package cotato.backend.common.exception;

public class InvalidApplicantAgeException extends IllegalArgumentException {
    public InvalidApplicantAgeException() {
        super("나이는 22~30세여야 합니다.");
    }
}

