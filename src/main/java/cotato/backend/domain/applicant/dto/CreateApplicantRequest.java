package cotato.backend.domain.applicant.dto;

import cotato.backend.domain.applicant.Applicant;

import java.time.LocalDateTime;

public record CreateApplicantRequest(String name, int age, String phoneNumber) {

    public Applicant toEntity() {
        return Applicant.builder()
                .name(name)
                .age(age)
                .phoneNumber(phoneNumber)
                .build();
    }
}
