package cotato.backend.domain.applicant;

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
            throw new IllegalArgumentException("이름은 2~10자여야 합니다.");
        }
    }

    private void validateAge(int age) {
        if (age < 22 || age > 30) {
            throw new IllegalArgumentException("나이는 22~30세여야 합니다.");
        }
    }

    private void validatePhoneNumber(String phoneNumber) {
        if (phoneNumber == null || !phoneNumber.startsWith("010") || phoneNumber.length() != 11) {
            throw new IllegalArgumentException("휴대폰 번호는 010으로 시작하는 11자리여야 합니다.");
        }
    }

}
