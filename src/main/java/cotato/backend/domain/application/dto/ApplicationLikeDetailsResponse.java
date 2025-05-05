package cotato.backend.domain.application.dto;

import cotato.backend.domain.admin.Admin;

import java.util.List;

public record ApplicationLikeDetailsResponse(
        Long applicationId,
        List<Admin> adminList,
        int likeCount
) {
}
