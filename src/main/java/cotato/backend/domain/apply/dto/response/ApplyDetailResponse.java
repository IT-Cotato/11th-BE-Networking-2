package cotato.backend.domain.apply.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import cotato.backend.domain.apply.entity.Apply;
import cotato.backend.domain.apply.entity.PartType;

import java.time.LocalDateTime;

public record ApplyDetailResponse(
        String name,
        int generation,
        int age,
        PartType part,
        int participationScore,
        int growthScore,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
        LocalDateTime submitTime
) {
    public static ApplyDetailResponse from(Apply apply) {
        return new ApplyDetailResponse(
                apply.getName(),
                apply.getGeneration(),
                apply.getAge(),
                apply.getPart(),
                apply.getParticipationScore(),
                apply.getGrowthScore(),
                apply.getSubmitTime()
        );
    }
}

