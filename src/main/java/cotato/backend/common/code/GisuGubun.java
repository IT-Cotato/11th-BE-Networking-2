package cotato.backend.common.code;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public enum GisuGubun {
    GISU_1("00201"),
    GISU_2("00202"),
    GISU_3("00203"),
    GISU_4("00204"),
    GISU_5("00205"),
    GISU_6("00206"),
    GISU_7("00207"),
    GISU_8("00208"),
    GISU_9("00209"),
    GISU_10("00210"),
    GISU_11("00211");

    private final String code;
    private final String name;

    GisuGubun(String code) {
        this.code = code;
        this.name = name();
    }
    public static GisuGubun fromCode(String code) {
        for (GisuGubun gisuGubun : values()) {
            if (gisuGubun.getCode().equals(code)) {
                return gisuGubun;
            }
        }
        throw new IllegalArgumentException("Invalid code: " + code);
    }

    @JsonValue
    public Map<String, String> toJson() {
        Map<String, String> map = new HashMap<>();

        map.put("code", code);

        String numberPart = this.name.replaceAll("[^0-9]", "");
        map.put("name", numberPart + "기" );

        return map;
    }
}
