package cotato.backend.domain.application.dto;

import cotato.backend.domain.application.enums.Part;

public record CreateApplicationRequestDto(
        String name,
        int age,
        String phoneNumber,
        int generation,
        Part part,
        int participation,
        int growth
) {}
