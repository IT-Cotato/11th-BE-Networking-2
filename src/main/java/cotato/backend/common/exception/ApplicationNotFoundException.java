package cotato.backend.common.exception;

public class ApplicationNotFoundException extends IllegalArgumentException {
    public ApplicationNotFoundException() {
        super("지원서를 찾을 수 없습니다.");
    }
}
