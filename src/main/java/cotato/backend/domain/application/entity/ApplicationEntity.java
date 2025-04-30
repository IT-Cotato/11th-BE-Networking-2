package cotato.backend.domain.application.entity;

import java.time.LocalDateTime;

import cotato.backend.common.exception.EntityValidationException;
import cotato.backend.common.exception.ErrorCode;
import cotato.backend.domain.example.entity.Part;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "application")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApplicationEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private Integer generation;

	@Column(nullable = false)
	private Integer age;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Part part;

	@Column(nullable = false)
	private Integer participationScore;

	@Column(nullable = false)
	private Integer growthMotivation;

	@Column(nullable = false)
	private String phoneNumber;

	@Column(nullable = false)
	private LocalDateTime applicationTime;

	@Column(nullable = false)
	private Integer likeCount;

	@Builder
	public ApplicationEntity(
		String name,
		Integer generation,
		Integer age,
		Part part,
		Integer participationScore,
		Integer growthMotivation,
		String phoneNumber
	) {

		validateName(name);
		validateGeneration(generation);
		validateAge(age);
		validatePart(part);
		validateParticipationScore(participationScore);
		validateGrowthMotivation(growthMotivation);
		validatePhoneNumber(phoneNumber);

		this.name = name;
		this.generation = generation;
		this.age = age;
		this.part = part;
		this.participationScore = participationScore;
		this.growthMotivation = growthMotivation;
		this.phoneNumber = phoneNumber;
		this.applicationTime = LocalDateTime.now();
		this.likeCount = 0;
	}

	private void validateName(String name) {
		if (name == null || name.isBlank()) {
			throw new EntityValidationException(ErrorCode.NAME_REQUIRED);
		}

		if (name.length() < 2 || name.length() > 10) {
			throw new EntityValidationException(ErrorCode.INVALID_NAME_LENGTH);
		}
	}

	private void validateGeneration(Integer generation) {
		if (generation == null) {
			throw new EntityValidationException(ErrorCode.GENERATION_REQUIRED);
		}

		if (generation < 1) {
			throw new EntityValidationException(ErrorCode.INVALID_GENERATION_RANGE);
		}
	}

	private void validateAge(Integer age) {
		if (age == null) {
			throw new EntityValidationException(ErrorCode.AGE_REQUIRED);
		}

		if (age < 22 || age > 30) {
			throw new EntityValidationException(ErrorCode.INVALID_AGE_RANGE);
		}
	}

	private void validatePart(Part part) {
		if (part == null) {
			throw new EntityValidationException(ErrorCode.PART_REQUIRED);
		}
	}

	private void validateParticipationScore(Integer participationScore) {
		if (participationScore == null) {
			throw new EntityValidationException(ErrorCode.PARTICIPATION_SCORE_REQUIRED);
		}

		if (participationScore < 0 || participationScore > 10) {
			throw new EntityValidationException(ErrorCode.INVALID_SCORE_RANGE);
		}
	}

	private void validateGrowthMotivation(Integer growthMotivation) {
		if (growthMotivation == null) {
			throw new EntityValidationException(ErrorCode.GROWTH_MOTIVATION_SCORE_REQUIRED);
		}

		if (growthMotivation < 0 || growthMotivation > 10) {
			throw new EntityValidationException(ErrorCode.INVALID_SCORE_RANGE);
		}
	}

	private void validatePhoneNumber(String phoneNumber) {
		if (phoneNumber == null || phoneNumber.isBlank()) {
			throw new EntityValidationException(ErrorCode.PHONE_NUMBER_REQUIRED);
		}

		if (phoneNumber.length() != 11) {
			throw new EntityValidationException(ErrorCode.INVALID_PHONE_NUMBER_LENGTH);
		}

		if (!phoneNumber.startsWith("010")) {
			throw new EntityValidationException(ErrorCode.INVALID_PHONE_NUMBER_PREFIX);
		}

		if (!phoneNumber.substring(3).matches("\\d{8}")) {
			throw new EntityValidationException(ErrorCode.INVALID_PHONE_NUMBER_DIGIT);
		}
	}

	public void incrementLikeCount() {
		this.likeCount++;
	}

}
