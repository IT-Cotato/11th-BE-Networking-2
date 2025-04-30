package cotato.backend.common.exception;

public class LikeAlreadyExistsException extends IllegalArgumentException {
    public LikeAlreadyExistsException() {
        super("이미 좋아요를 눌렀습니다");
    }
}
