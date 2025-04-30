package cotato.backend.domain.application.dto;


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
        LocalDateTime submittedAt
) {}

