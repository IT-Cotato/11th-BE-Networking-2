package cotato.backend.common.exception;

public class InvalidSortOptionException extends IllegalArgumentException {
    public InvalidSortOptionException() {
        super("유효하지 않은 정렬 기준입니다.");
    }
}
