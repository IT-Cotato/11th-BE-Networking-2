package cotato.backend.adapter.in.dto.response;

import java.util.UUID;

public record RegisterFormResponse(
        UUID id
) {
    public static RegisterFormResponse of(UUID id) {
        return new RegisterFormResponse(id);
    }
}
