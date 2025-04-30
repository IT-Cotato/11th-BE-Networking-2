package cotato.backend.domain.admin.dto;

import cotato.backend.domain.admin.Admin;
import cotato.backend.domain.applicant.Applicant;
import cotato.backend.domain.application.enums.Part;

public record CreateAdminRequestDto(
        String name,
        String phoneNumber,
        int generation,
        Part part
) {
    public Admin toEntity() {
        return Admin.builder()
                .name(name)
                .phoneNumber(phoneNumber)
                .generation(generation)
                .part(part)
                .build();
    }
}
