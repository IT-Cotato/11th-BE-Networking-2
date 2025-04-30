package cotato.backend.common.exception;

public class InvalidGenerationException extends IllegalArgumentException {
    public InvalidGenerationException() {
        super("지원 기수는 1 이상이어야 합니다.");
    }
}
