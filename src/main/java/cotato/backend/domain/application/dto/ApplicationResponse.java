package cotato.backend.domain.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import cotato.backend.domain.application.Application;
import cotato.backend.domain.application.enums.Part;

import java.time.LocalDateTime;

public record ApplicationResponse(
        Long applicationId,
        int generation,
        Part part,
        int participation,
        int growth,
        String phoneNumber,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
        LocalDateTime submittedAt

) {
    public static ApplicationResponse from(Application application) {
        return new ApplicationResponse(
                application.getId(),
                application.getGeneration(),
                application.getPart(),
                application.getParticipation(),
                application.getGrowth(),
                application.getApplicant().getPhoneNumber(),
                application.getSubmittedAt()
        );
    }
}