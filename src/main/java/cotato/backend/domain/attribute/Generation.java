package cotato.backend.domain.attribute;

import cotato.backend.exception.invalidInfo.InvalidGenerationException;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Generation {
    public static final int LOWER_BOUND = 1;
    private int generation;

    public Generation(int Generation) {
        validate(Generation);
        this.generation = Generation;
    }

    private void validate(int generation) {
        if (!(generation >= LOWER_BOUND)) {
            InvalidGenerationException.raise();
        }
    }
}
