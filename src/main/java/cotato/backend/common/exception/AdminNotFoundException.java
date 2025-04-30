package cotato.backend.common.exception;

public class AdminNotFoundException extends IllegalArgumentException {
    public AdminNotFoundException() {
        super("관리자를 찾을 수 없습니다.");
    }
}
