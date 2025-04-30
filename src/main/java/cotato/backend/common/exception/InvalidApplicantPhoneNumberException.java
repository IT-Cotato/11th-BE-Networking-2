package cotato.backend.common.exception;

public class InvalidApplicantPhoneNumberException extends IllegalArgumentException {
    public InvalidApplicantPhoneNumberException() {
        super("휴대폰 번호는 010으로 시작하는 11자리여야 합니다.");
    }
}