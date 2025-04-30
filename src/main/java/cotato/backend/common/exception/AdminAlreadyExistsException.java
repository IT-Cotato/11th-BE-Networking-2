package cotato.backend.common.exception;

public class AdminAlreadyExistsException extends IllegalArgumentException {
    public AdminAlreadyExistsException() {
        super("이미 존재하는 관리자입니다.");
    }
}
