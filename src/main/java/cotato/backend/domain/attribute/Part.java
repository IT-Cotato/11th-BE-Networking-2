package cotato.backend.domain.attribute;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import cotato.backend.exception.invalidInfo.InvalidPartException;
import java.util.Arrays;

public enum Part {
    PM("기획"), DESIGN("디자인"), FRONTEND("프론트엔드"), BACKEND("백엔드");

    private final String value;

    Part(String value) {
        this.value = value;
    }

    @JsonValue
    public String value() {
        return value;
    }

    @JsonCreator
    public static Part convert(String value) {
        return Arrays.stream(Part.values())
                .filter(part -> part.value.equals(value))
                .findFirst()
                .orElseThrow(InvalidPartException::construct);
    }
}
