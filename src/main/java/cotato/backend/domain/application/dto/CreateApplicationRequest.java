package cotato.backend.domain.application.dto;

import cotato.backend.domain.applicant.Applicant;
import cotato.backend.domain.application.Application;
import cotato.backend.domain.application.enums.Part;

public record CreateApplicationRequest(Applicant applicant, int generation, Part part, int participation, int growth) {

    public Application toEntity() {
        return Application.builder()
                .applicant(applicant)
                .generation(generation)
                .part(part)
                .participation(participation)
                .growth(growth)
                .build();
    }
}
