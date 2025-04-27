package cotato.backend.domain.example.dto.response;

import cotato.backend.common.code.ApplyPartGubun;
import cotato.backend.common.code.GisuGubun;
import cotato.backend.domain.example.entity.Apply;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@Getter
public class ApplyDetailResponse {
    private Long applyId;
    private GisuGubun gisuGubun;
    private ApplyPartGubun applyPartGubun;
    private int participationLevel;
    private int growLevel;
    private int likeCount;
    private String phoneNumber;
    private LocalDateTime applyTime;


    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public static ApplyDetailResponse from(Apply apply) {
        return ApplyDetailResponse.builder()
                .applyId(apply.getApplyId())
                .gisuGubun(apply.getGisuGubun())
                .applyPartGubun(apply.getApplyPartGubun())
                .participationLevel(apply.getParticipationLevel())
                .growLevel(apply.getGrowLevel())
                .phoneNumber(apply.getPhoneNumber())
                .applyTime(apply.getApplyTime())
                .build();
    }


}
//지원자의 식별번호
//지원 기수
//파트
//참여 적극성
//성장 의지
//받은 좋아요 수
//휴대폰 번호
//서류 제출 시간