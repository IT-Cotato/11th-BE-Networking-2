package cotato.backend.common;

import cotato.backend.domain.attribute.Age;
import cotato.backend.domain.attribute.Engagement;
import cotato.backend.domain.attribute.Generation;
import cotato.backend.domain.attribute.GrowthDesire;
import cotato.backend.domain.attribute.Name;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum FailureDetail {
    INVALID_NAME(HttpStatus.BAD_REQUEST,
            String.format("이름은 한글로 %d자 이상 %d자 이하이어야 합니다.", Name.UPPER_BOUND, Name.UPPER_BOUND)),
    INVALID_GENERATION(HttpStatus.BAD_REQUEST, String.format("지원 기수는 %d 이상이어야 합니다.", Generation.LOWER_BOUND)),
    INVALID_AGE(HttpStatus.BAD_REQUEST, String.format("나이는 %d세 이상 %d세 이하이어야 합니다.", Age.LOWER_BOUND, Age.UPPER_BOUND)),
    INVALID_PART(HttpStatus.BAD_REQUEST, "파트는 기획, 디자이너, 프론트엔드, 백엔드 중 하나이어야 합니다."),
    INVALID_ENGAGEMENT(HttpStatus.BAD_REQUEST,
            String.format("참여 적극성은 %d 이상 %d 이하이어야 합니다.", Engagement.LOWER_BOUND, Engagement.UPPER_BOUND)),
    INVALID_GROWTH_DESIRE(HttpStatus.BAD_REQUEST,
            String.format("성장 의지는 %d 이상 %d 이하이어야 합니다.", GrowthDesire.LOWER_BOUND, GrowthDesire.UPPER_BOUND)),
    INVALID_PHONE_NUMER(HttpStatus.BAD_REQUEST, "올바르지 않은 휴대폰 번호입니다."),
    INVALID_SORT_OPTION(HttpStatus.BAD_REQUEST,
            "정렬 기준은 OLDEST, LATEST, MOST_LIKE, HIGHEST_ENGAGEMENT, HIGHEST_GROWTH_DESIRE 중 하나이어야 합니다."),

    NOT_FOUND_FORM(HttpStatus.NOT_FOUND, "존재하지 않는 지원자 서류입니다."),
    NOT_FOUND_ADMIN(HttpStatus.NOT_FOUND, "존재하지 않는 운영진입니다."),

    ALREADY_LIKED(HttpStatus.CONFLICT, "이미 좋아요를 눌렀습니다.");

    private final HttpStatus status;
    private final String message;

    FailureDetail(HttpStatus status, String message) {
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
