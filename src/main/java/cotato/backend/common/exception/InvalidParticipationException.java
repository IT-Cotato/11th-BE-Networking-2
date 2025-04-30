package cotato.backend.common.exception;

public class InvalidParticipationException extends IllegalArgumentException {
    public InvalidParticipationException() {
        super("참여 적극성은 0~10 사이여야 합니다.");
    }
}
