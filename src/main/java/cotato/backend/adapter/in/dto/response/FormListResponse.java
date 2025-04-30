package cotato.backend.adapter.in.dto.response;

import java.util.List;

public record FormListResponse(
        List<FormResponse> forms, PaginationInfoResponse paginationInfo
) {
    public static FormListResponse of(List<FormResponse> forms, PaginationInfoResponse paginationInfo) {
        return new FormListResponse(forms, paginationInfo);
    }
}
