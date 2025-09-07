package cotato.backend.domain.example.dto.response;

import cotato.backend.common.code.ApplyPartGubun;
import cotato.backend.common.code.GisuGubun;
import cotato.backend.common.dto.PageResponse;
import cotato.backend.domain.example.entity.Apply;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.querydsl.QPageRequest;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@Getter @ToString
public class ApplyListResponse {
    private Long applyId;
    private String name;
    private GisuGubun gisuGubun;
    private int age;
    private ApplyPartGubun applyPartGubun;
    private int participationLevel;
    private int growLevel;
    private Long likeCount;
    private LocalDateTime applyTime;


    public static ApplyListResponse from(Apply apply) {
        return ApplyListResponse.builder()
            .applyId(apply.getApplyId())
            .name(apply.getName())
            .gisuGubun(apply.getGisuGubun())
            .age(apply.getAge())
            .applyPartGubun(apply.getApplyPartGubun())
            .participationLevel(apply.getParticipationLevel())
            .applyTime(apply.getApplyTime())
            .growLevel(apply.getGrowLevel())
            .build();
    }

    public void setLikeCount(Long likeCount) {
        this.likeCount = likeCount;
    }
}

//지원자의 식별번호
//지원 기수
//파트
//참여 적극성
//성장 의지
//받은 좋아요 수
//서류 제출 시간