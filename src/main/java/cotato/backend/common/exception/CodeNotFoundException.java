package cotato.backend.common.exception;

public class CodeNotFoundException extends AppException {
    public CodeNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
