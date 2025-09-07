package cotato.backend.domain.example.dto.request;

import cotato.backend.common.code.ApplyPartGubun;
import cotato.backend.common.code.GisuGubun;
import cotato.backend.domain.example.entity.Apply;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ApplyCreateRequest {
    private String name;
    private String gisu;
    private int age;
    private String applyPartGubun;
    private String phoneNumber;
    private int participationLevel;
    private int growLevel;

    public Apply toEntity() {
        return Apply.builder()
            .name(this.getName())
            .gisuGubun(GisuGubun.fromCode(gisu))
            .age(this.getAge())
            .phoneNumber(this.getPhoneNumber())
            .participationLevel(this.getParticipationLevel())
            .applyPartGubun(ApplyPartGubun.fromCode(this.applyPartGubun))
            .growLevel(this.getGrowLevel())
            .build();
    }

//    void validate() {
//
//    }


}
