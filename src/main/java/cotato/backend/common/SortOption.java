package cotato.backend.common;

import com.fasterxml.jackson.annotation.JsonCreator;
import cotato.backend.exception.invalidInfo.InvalidSortOptionException;
import java.util.Arrays;

public enum SortOption {
    OLDEST, LATEST, MOST_LIKE, HIGHEST_ENGAGEMENT, HIGHEST_GROWTH_DESIRE;

    @JsonCreator
    public static SortOption convert(String value) {
        return Arrays.stream(SortOption.values())
                .filter(sortOption -> sortOption.name().equals(value))
                .findFirst()
                .orElseThrow(InvalidSortOptionException::construct);
    }
}
