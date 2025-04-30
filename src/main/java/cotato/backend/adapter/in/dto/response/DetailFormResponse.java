package cotato.backend.adapter.in.dto.response;

import cotato.backend.domain.Form;
import cotato.backend.domain.attribute.Part;
import java.time.LocalDateTime;
import java.util.UUID;

public record DetailFormResponse(
        UUID id, int generation, Part part, int engagement, int growthDesire, int likeCount, String phoneNumber,
        LocalDateTime submittedAt
) {
    public static DetailFormResponse of(Form form) {
        return new DetailFormResponse(form.getId(),
                form.getGeneration().getGeneration(),
                form.getPart(),
                form.getGeneration().getGeneration(),
                form.getGrowthDesire().getGrowthDesire(),
                form.getLikes().size(),
                form.getPhoneNumber().getPhoneNumber(),
                form.getSubmittedAt()
        );
    }
}
