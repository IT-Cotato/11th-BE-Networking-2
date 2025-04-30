package cotato.backend.domain.attribute;

import cotato.backend.exception.invalidInfo.InvalidPhoneNumberException;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PhoneNumber {
    public static final String FORMAT_CONSTRAINT = "^010\\d{8}$";
    private String phoneNumber;

    public PhoneNumber(String phoneNumber) {
        validate(phoneNumber);
        this.phoneNumber = phoneNumber;
    }

    private void validate(String phoneNumber) {
        if (!(phoneNumber.matches(FORMAT_CONSTRAINT))) {
            InvalidPhoneNumberException.raise();
        }
    }
}
