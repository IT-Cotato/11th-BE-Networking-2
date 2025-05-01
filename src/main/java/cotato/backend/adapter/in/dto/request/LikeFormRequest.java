package cotato.backend.adapter.in.dto.request;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record LikeFormRequest(
        @NotNull(message = "운영진 ID가 입력되지 않았습니다.")
        UUID id
) {
}
