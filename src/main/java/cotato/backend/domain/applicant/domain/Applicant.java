package cotato.backend.domain.applicant.domain;

import java.time.LocalDateTime;

import cotato.backend.domain.applicant.dto.CreateApplicant;
import cotato.backend.domain.applicant.enums.PartType;
import lombok.Getter;

@Getter
public class Applicant {

	private final Long id;
	private final String name;
	private final Long generation;
	private final Long age;
	private final PartType part;
	private final Long participationLevel;
	private final Long growthWillingness;
	private final String phoneNumber;
	private final LocalDateTime submissionTime;

	private Applicant(Long id, String name, Long generation, Long age, PartType part,
		Long participationLevel, Long growthWillingness,
		String phoneNumber, LocalDateTime submissionTime) {
		this.id = id;
		this.name = name;
		this.generation = generation;
		this.age = age;
		this.part = part;
		this.participationLevel = participationLevel;
		this.growthWillingness = growthWillingness;
		this.phoneNumber = phoneNumber;
		this.submissionTime = submissionTime;
	}

	// 생성 요청시 사용하는 팩토리 메서드
	public static Applicant createNew(CreateApplicant create) {
		return new Applicant(null, create.name(), create.generation(), create.age(), create.part(),
			create.participationLevel(), create.growthWillingness(), create.phoneNumber(),
			LocalDateTime.now());
	}

	// 기존 데이터로부터 도메인 객체 재구성시 사용하는 팩토리 메서드
	public static Applicant reconstitute(Long id, String name, Long generation, Long age,
		PartType part, Long participationLevel, Long growthWillingness,
		String phoneNumber, LocalDateTime submissionTime) {
		return new Applicant(id, name, generation, age, part, participationLevel,
			growthWillingness, phoneNumber, submissionTime);
	}
}