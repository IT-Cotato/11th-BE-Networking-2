package cotato.backend.adapter.in.dto.response;

import cotato.backend.domain.Form;
import cotato.backend.domain.attribute.Part;
import java.time.LocalDateTime;
import java.util.UUID;

public record FormResponse(
        UUID id, int generation, Part part, int engagement, int growthDesire, int likeCount,
        LocalDateTime submittedAt
) {
    public static FormResponse of(Form form) {
        return new FormResponse(form.getId(),
                form.getGeneration().getGeneration(),
                form.getPart(),
                form.getGeneration().getGeneration(),
                form.getGrowthDesire().getGrowthDesire(),
                form.getLikes().size(),
                form.getSubmittedAt()
        );
    }
}
