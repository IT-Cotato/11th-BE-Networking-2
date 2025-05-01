package cotato.backend.domain.attribute;

import cotato.backend.exception.invalidInfo.InvalidGrowthDesireException;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GrowthDesire {
    public static final int LOWER_BOUND = 0;
    public static final int UPPER_BOUND = 10;
    private int growthDesire;

    public GrowthDesire(int growthDesire) {
        validate(growthDesire);
        this.growthDesire = growthDesire;
    }

    private void validate(int growthDesire) {
        if (!(growthDesire >= LOWER_BOUND && growthDesire <= UPPER_BOUND)) {
            InvalidGrowthDesireException.raise();
        }
    }
}
