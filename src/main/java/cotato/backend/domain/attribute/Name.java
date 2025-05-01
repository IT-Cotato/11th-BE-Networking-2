package cotato.backend.domain.attribute;

import cotato.backend.exception.invalidInfo.InvalidNameException;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Name {
    public static final int LOWER_BOUND = 2;
    public static final int UPPER_BOUND = 10;
    public static final String FORMAT_CONSTRAINT = String.format("^[가-힣]{%d,%d}$", LOWER_BOUND, UPPER_BOUND);
    private String name;

    public Name(String name) {
        validate(name);
        this.name = name;
    }

    private void validate(String name) {
        if (!name.matches(FORMAT_CONSTRAINT)) {
            InvalidNameException.raise();
        }
    }
}
