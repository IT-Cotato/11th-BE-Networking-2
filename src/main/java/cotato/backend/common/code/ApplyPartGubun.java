package cotato.backend.common.code;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

@Getter
public enum ApplyPartGubun  {
    DESIGN("00101"),
    FRONTEND("00102"),
    BACKEND("00103"),
    PLANNER("00104");

    private final String code;
    private final String name;

    ApplyPartGubun(String code) {
        this.code = code;
        this.name = name();
    }
    public static ApplyPartGubun fromCode(String code) {
        for (ApplyPartGubun partGubun : values()) {
            if (partGubun.getCode().equals(code)) {
                return partGubun;
            }
        }
        throw new IllegalArgumentException("Invalid code: " + code);
    }

    @JsonValue
    public Map<String, String> toJson() {
        Map<String, String> map = new HashMap<>();
        map.put("code", code);
        map.put("name", name);
        return map;
    }

}
