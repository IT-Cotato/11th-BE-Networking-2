package cotato.backend.domain.applicant.adapter.out.persistence.entity;

import java.time.LocalDateTime;

import cotato.backend.domain.applicant.domain.model.Applicant;
import cotato.backend.domain.applicant.domain.type.Part;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "applicants")
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApplicantJpaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; // 지원자 ID

	@Column(name = "name", nullable = false)
	private String name; // 이름

	@Column(name = "generation", nullable = false)
	private int generation; // 지원 기수

	@Column(name = "age", nullable = false)
	private int age; // 나이

	@Enumerated(EnumType.STRING)
	@Column(name = "part", nullable = false)
	private Part part; // 지원 파트

	@Column(name = "passion", nullable = false)
	private int passion;    // 참여 적극성

	@Column(name = "growth", nullable = false)
	private int growth; 	// 성장 의지

	@Column(name = "phone", nullable = false)
	private String phone; 	// 전화번호

	@Column(name = "submitted_at", nullable = false)
	private LocalDateTime submittedAt;

	@Column(name = "likes", nullable = false)
	private int likes; // 받은 좋아요, 0으로 초기화

	public static ApplicantJpaEntity from(Applicant applicant) {
		return new ApplicantJpaEntity(
			applicant.getId(),
			applicant.getName(),
			applicant.getGeneration(),
			applicant.getAge(),
			applicant.getPart(),
			applicant.getPassion(),
			applicant.getGrowth(),
			applicant.getPhone(),
			applicant.getSubmittedAt(),
			applicant.getLikes()
		);
	}

	public void increaseLikes() {
		this.likes++;
	}

}
