package cotato.backend.domain.attribute;

import cotato.backend.exception.invalidInfo.InvalidAgeException;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Age {
    public static final int LOWER_BOUND = 22;
    public static final int UPPER_BOUND = 30;
    private int age;

    public Age(int age) {
        validate(age);
        this.age = age;
    }

    private void validate(int age) {
        if (!(age >= LOWER_BOUND && age <= UPPER_BOUND)) {
            InvalidAgeException.raise();
        }
    }
}
