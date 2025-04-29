package cotato.backend.domain.apply.dto.request;

import cotato.backend.domain.apply.entity.PartType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor //모든 필드 값을 파라미터로 받는 생성자를 생성
@NoArgsConstructor //파라미터가 없는 기본 생성자를 생성
public class ApplyCreateRequest {

    private String name;
    private int generation;
    private int age;
    private PartType part;
    private int participationScore;
    private int growthScore;
    private String phoneNumber;
}
