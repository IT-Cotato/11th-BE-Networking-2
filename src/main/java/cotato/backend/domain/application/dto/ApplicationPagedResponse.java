package cotato.backend.domain.application.dto;

import java.util.List;

public record ApplicationPagedResponse(
        List<ApplicationListResponse> content,
        int currentPage,
        int pageSize,
        String sortBy,
        long totalElements,
        int totalPages

) {}
