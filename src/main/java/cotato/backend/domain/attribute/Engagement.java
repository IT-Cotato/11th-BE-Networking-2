package cotato.backend.domain.attribute;

import cotato.backend.exception.invalidInfo.InvalidEngagementException;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Engagement {
    public static final int LOWER_BOUND = 0;
    public static final int UPPER_BOUND = 10;
    private int engagement;

    public Engagement(int engagement) {
        validate(engagement);
        this.engagement = engagement;
    }

    private void validate(int engagement) {
        if (!(engagement >= LOWER_BOUND && engagement <= UPPER_BOUND)) {
            InvalidEngagementException.raise();
        }
    }
}
