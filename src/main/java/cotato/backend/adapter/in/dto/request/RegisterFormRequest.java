package cotato.backend.adapter.in.dto.request;

import cotato.backend.domain.attribute.Part;

public record RegisterFormRequest(
        String name, int generation, int age, Part part, int engagement, int growthDesire, String phoneNumber
) {
}
