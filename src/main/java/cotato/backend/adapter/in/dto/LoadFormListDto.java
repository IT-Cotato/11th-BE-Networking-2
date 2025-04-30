package cotato.backend.adapter.in.dto;

import cotato.backend.common.SortOption;

public record LoadFormListDto(
        int pageNumber, SortOption sortOption
) {
    public static LoadFormListDto of(int pageNumber, SortOption sortOption) {
        return new LoadFormListDto(pageNumber, sortOption);
    }
}
