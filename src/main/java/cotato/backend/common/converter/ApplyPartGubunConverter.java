package cotato.backend.common.converter;

import cotato.backend.common.code.ApplyPartGubun;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ApplyPartGubunConverter implements AttributeConverter<ApplyPartGubun, String> {
    
    //DB에 저장할때는 code만
    @Override
    public String convertToDatabaseColumn(ApplyPartGubun attribute) {
        return attribute != null ? attribute.getCode() : null;
    }

    //DB에서 가져올때는 enum으로
    @Override
    public ApplyPartGubun convertToEntityAttribute(String dbData) {
        return dbData != null ? ApplyPartGubun.fromCode(dbData) : null;
    }
}
