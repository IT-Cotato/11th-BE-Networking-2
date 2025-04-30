package cotato.backend.domain.applicant;

import cotato.backend.common.exception.InvalidApplicantAgeException;
import cotato.backend.common.exception.InvalidApplicantNameException;
import cotato.backend.common.exception.InvalidApplicantPhoneNumberException;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="applicant")
public class Applicant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "applicant_id")
    private Long id;

    @Column(name = "applicant_name")
    private String name;

    @Column(name = "applicant_age")
    private int age;

    @Column(name = "applicant_phoneNumber")
    private String phoneNumber;

    @Builder
    public Applicant(String name, int age, String phoneNumber) {
        validateName(name);
        validateAge(age);
        validatePhoneNumber(phoneNumber);

        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
    }

    private void validateName(String name) {
        if (name == null || name.length() < 2 || name.length() > 10) {
            throw new InvalidApplicantNameException();
        }
    }

    private void validateAge(int age) {
        if (age < 22 || age > 30) {
            throw new InvalidApplicantAgeException();
        }
    }

    private void validatePhoneNumber(String phoneNumber) {
        if (phoneNumber == null || !phoneNumber.startsWith("010") || phoneNumber.length() != 11) {
            throw new InvalidApplicantPhoneNumberException();
        }
    }

}
