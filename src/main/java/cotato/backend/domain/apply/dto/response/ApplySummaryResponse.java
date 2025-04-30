package cotato.backend.domain.apply.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import cotato.backend.domain.apply.entity.Apply;
import cotato.backend.domain.apply.entity.PartType;

import java.time.LocalDateTime;

public record ApplySummaryResponse(
        Long id,
        int generation,
        PartType part,
        int participationScore,
        int growthScore,
        int likeCount,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
        LocalDateTime submitTime
) {
    public static ApplySummaryResponse from(Apply apply) {
        return new ApplySummaryResponse(
                apply.getId(),
                apply.getGeneration(),
                apply.getPart(),
                apply.getParticipationScore(),
                apply.getGrowthScore(),
                apply.getLikeCount(),
                apply.getSubmitTime()
        );
    }
}


