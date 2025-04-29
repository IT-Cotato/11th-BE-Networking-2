package cotato.backend.domain.apply.dto.response;

import cotato.backend.domain.apply.entity.PartType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ApplyCreateResponse {
    private String name;
    private int generation;
    private int age;
    private PartType part;
    private int participationScore;
    private int growthScore;
    private String phoneNumber;
}
