package cotato.backend.domain.apply.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;


import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum PartType {
    PLANNER("PLANNER"),
    DESIGNER("DESIGNER"),
    FRONTEND("FRONTEND"),
    BACKEND("BACKEND"),;

    @JsonValue private final String part;

    @JsonCreator
    public static PartType fromString(String value) {
        return Arrays.stream(PartType.values())
                .filter(e -> e.part.equalsIgnoreCase(value))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Invalid part type: " + value));
    }
}
