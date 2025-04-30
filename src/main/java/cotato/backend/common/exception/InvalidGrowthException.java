package cotato.backend.common.exception;

public class InvalidGrowthException extends IllegalArgumentException {
    public InvalidGrowthException() {
        super("성장 의지는 0~10 사이여야 합니다.");
    }
}
