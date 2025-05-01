package cotato.backend.domain.applicant.dto;

import cotato.backend.domain.applicant.entity.Applicant.Part;

public record ApplicantRequest(
        String name,
        int generation,
        int age,
        Part part,
        int participation,
        int growth,
        String phone
) {}
