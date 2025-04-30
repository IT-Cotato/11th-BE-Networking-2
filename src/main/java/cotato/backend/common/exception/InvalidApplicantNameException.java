package cotato.backend.common.exception;

public class InvalidApplicantNameException extends IllegalArgumentException {
    public InvalidApplicantNameException() {
        super("이름은 2~10자여야 합니다.");
    }
}
