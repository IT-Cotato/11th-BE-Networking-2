package cotato.backend.common.converter;

import cotato.backend.common.code.ApplyPartGubun;
import cotato.backend.common.code.GisuGubun;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class GisuGubunConverter implements AttributeConverter<GisuGubun, String> {
    //DB에 저장할 때는 code값만 저장
    @Override
    public String convertToDatabaseColumn(GisuGubun attribute) {
        return attribute != null ? attribute.getCode() : null;
    }

    //DB에서 가져온 값으로 enum을 생성
    @Override
    public GisuGubun convertToEntityAttribute(String dbData) {
        return dbData != null ? GisuGubun.fromCode(dbData) : null;
    }
}
