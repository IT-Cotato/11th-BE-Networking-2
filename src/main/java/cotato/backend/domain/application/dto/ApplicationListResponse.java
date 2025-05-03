package cotato.backend.domain.application.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import cotato.backend.domain.application.Application;
import cotato.backend.domain.application.enums.Part;

import java.time.LocalDateTime;

public record ApplicationListResponse (
        Long applicantId,
        Long applicationId,
        int generation,
        Part part,
        int participation,
        int growth,
        int likeCount,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
        LocalDateTime submittedAt
) { }

