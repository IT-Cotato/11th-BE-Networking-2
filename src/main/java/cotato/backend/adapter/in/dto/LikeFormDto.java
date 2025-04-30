package cotato.backend.adapter.in.dto;

import cotato.backend.adapter.in.dto.request.LikeFormRequest;
import java.util.UUID;

public record LikeFormDto(
        UUID adminId, UUID formId
) {
    public static LikeFormDto of(LikeFormRequest likeFormRequest, UUID formId) {
        return new LikeFormDto(likeFormRequest.id(), formId);
    }
}
