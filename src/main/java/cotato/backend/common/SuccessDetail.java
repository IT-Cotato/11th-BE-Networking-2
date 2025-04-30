package cotato.backend.common;

import org.springframework.http.HttpStatus;

public enum SuccessDetail {
    REGISTERED(HttpStatus.CREATED, "지원자 서류를 등록했습니다."),
    LIKED(HttpStatus.CREATED, "지원자 서류에 좋아요를 눌렀습니다."),
    RETRIEVED_DETAIL(HttpStatus.OK, "지원자 서류를 세부 조회했습니다."),
    RETRIEVED_LIST(HttpStatus.OK, "지원자 서류 목록을 조회했습니다.");

    private final HttpStatus status;
    private final String message;

    SuccessDetail(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public int status() {
        return status.value();
    }

    public String message() {
        return message;
    }
}
