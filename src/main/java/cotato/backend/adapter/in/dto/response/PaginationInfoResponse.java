package cotato.backend.adapter.in.dto.response;

import cotato.backend.common.SortOption;

public record PaginationInfoResponse(
        int pageNumber, int pageSize, SortOption sortOption, int totalElement, int totalPage
) {
    public static PaginationInfoResponse of(int pageNumber, int pageSize, SortOption sortOption, int totalElement,
                                            int totalPage) {
        return new PaginationInfoResponse(pageNumber, pageSize, sortOption, totalElement, totalPage);
    }
}
