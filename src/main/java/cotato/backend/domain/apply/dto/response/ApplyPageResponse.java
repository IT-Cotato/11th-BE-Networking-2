package cotato.backend.domain.apply.dto.response;

import org.springframework.data.domain.Page;

import java.util.List;

public record ApplyPageResponse(
        List<ApplySummaryResponse> applies,
        int currentPage,
        int pageSize,
        long totalElements,
        int totalPages,
        String sort
) {
    public static ApplyPageResponse from(Page<ApplySummaryResponse> page, String sort) {
        return new ApplyPageResponse(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                sort
        );
    }
}

