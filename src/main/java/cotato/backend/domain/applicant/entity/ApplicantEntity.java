package cotato.backend.domain.applicant.entity;

import java.time.LocalDateTime;

import cotato.backend.domain.applicant.domain.Applicant;
import cotato.backend.domain.applicant.enums.PartType;
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
@Getter
@Table(name = "applicant")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApplicantEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", nullable = false, length = 10)
	private String name;

	@Column(name = "generation", nullable = false)
	private Long generation;

	@Column(name = "age", nullable = false)
	private Long age;

	@Column(name = "part", nullable = false, length = 20)
	@Enumerated(EnumType.STRING)
	private PartType part;

	@Column(name = "participation_level", nullable = false)
	private Long participationLevel;

	@Column(name = "growth_willingness", nullable = false)
	private Long growthWillingness;

	@Column(name = "phone_number", nullable = false, unique = true, length = 11)
	private String phoneNumber;

	@Column(name = "submission_time", nullable = false)
	private LocalDateTime submissionTime;

	@Builder
	public ApplicantEntity(String name, Long generation, Long age, PartType part,
		Long participationLevel, Long growthWillingness,
		String phoneNumber, LocalDateTime submissionTime) {
		this.name = name;
		this.generation = generation;
		this.age = age;
		this.part = part;
		this.participationLevel = participationLevel;
		this.growthWillingness = growthWillingness;
		this.phoneNumber = phoneNumber;
		this.submissionTime = submissionTime;
	}

	public Applicant toDomain() {
		return Applicant.reconstitute(
			id,
			name,
			generation,
			age,
			part,
			participationLevel,
			growthWillingness,
			phoneNumber,
			submissionTime
		);
	}

	public static ApplicantEntity fromDomain(Applicant applicant) {
		return ApplicantEntity.builder()
			.name(applicant.getName())
			.generation(applicant.getGeneration())
			.age(applicant.getAge())
			.part(applicant.getPart())
			.participationLevel(applicant.getParticipationLevel())
			.growthWillingness(applicant.getGrowthWillingness())
			.phoneNumber(applicant.getPhoneNumber())
			.submissionTime(applicant.getSubmissionTime())
			.build();
	}
}