package cotato.backend.domain.applicant.dto;

import cotato.backend.domain.applicant.Applicant;

public record ApplicantDto(String name, int age, String phoneNumber) {

    public Applicant toEntity() {
        return Applicant.builder()
                .name(name)
                .age(age)
                .phoneNumber(phoneNumber)
                .build();
    }

}
