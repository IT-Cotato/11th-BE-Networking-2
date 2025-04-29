package cotato.backend.domain.applicant.domain.model;

import java.time.LocalDateTime;

import cotato.backend.common.exception.ErrorCode;
import cotato.backend.domain.applicant.domain.type.Part;
import cotato.backend.domain.applicant.exception.ApplicantException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Applicant {

	private static final int NAME_MIN_LENGTH = 2;
	private static final int NAME_MAX_LENGTH = 10;
	private static final int MIN_GENERATION = 1;
	private static final int MIN_AGE = 22;
	private static final int MAX_AGE = 30;
	private static final int MIN_PASSION = 0;
	private static final int MAX_PASSION = 10;
	private static final int MIN_GROWTH = 0;
	private static final int MAX_GROWTH = 10;
	private static final String PHONE_REGEX = "^010\\d{8}$";

	private final Long id;
	private final String name;
	private final int generation;
	private final int age;
	private final Part part;
	private final int passion;
	private final int growth;
	private final String phone;
	private final LocalDateTime submittedAt;
	private final int likes;

	public static Applicant withId(
		Long id, String name, int generation, int age, Part part,
		int passion, int growth, String phone, LocalDateTime submittedAt, int likes) {

		validate(name, generation, age, passion, growth, phone);

		return new Applicant(id, name, generation, age, part, passion, growth, phone, submittedAt, likes);
	}

	public static Applicant withoutId(
		String name, int generation, int age, Part part,
		int passion, int growth, String phone) {

		validate(name, generation, age, passion, growth, phone);

		return new Applicant(null, name, generation, age, part, passion, growth, phone, LocalDateTime.now(), 0);
	}

	private static void validate(String name, int generation, int age, int passion, int growth, String phone) {
		validateName(name);
		validateGeneration(generation);
		validateAge(age);
		validatePassion(passion);
		validateGrowth(growth);
		validatePhone(phone);
	}

	private static void validateName(String name) {
		if (name == null || name.length() < NAME_MIN_LENGTH || name.length() > NAME_MAX_LENGTH) {
			throw new ApplicantException(ErrorCode.APPLICANT_INVALID_NAME);
		}
	}

	private static void validateGeneration(int generation) {
		if (generation < MIN_GENERATION) {
			throw new ApplicantException(ErrorCode.APPLICANT_INVALID_GENERATION);
		}
	}

	private static void validateAge(int age) {
		if (age < MIN_AGE || age > MAX_AGE) {
			throw new ApplicantException(ErrorCode.APPLICANT_INVALID_AGE);
		}
	}

	private static void validatePassion(int passion) {
		if (passion < MIN_PASSION || passion > MAX_PASSION) {
			throw new ApplicantException(ErrorCode.APPLICANT_INVALID_PASSION);
		}
	}

	private static void validateGrowth(int growth) {
		if (growth < MIN_GROWTH || growth > MAX_GROWTH) {
			throw new ApplicantException(ErrorCode.APPLICANT_INVALID_GROWTH);
		}
	}

	private static void validatePhone(String phone) {
		if (phone == null || !phone.matches(PHONE_REGEX)) {
			throw new ApplicantException(ErrorCode.APPLICANT_INVALID_PHONE);
		}
	}

}
