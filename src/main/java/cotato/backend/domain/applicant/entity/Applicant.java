package cotato.backend.domain.applicant.entity;

import static cotato.backend.domain.applicant.enums.Constants.*;

import cotato.backend.common.entity.BaseEntity;
import cotato.backend.domain.applicant.enums.Part;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "applicant", indexes = {
	@Index(name = "idx_applicant_created_at", columnList = "created_at"),
	@Index(name = "idx_applicant_likes_created_at", columnList = "likes, created_at"),
	@Index(name = "idx_applicant_activity_created_at", columnList = "activity, created_at"),
	@Index(name = "idx_applicant_growth_created_at", columnList = "growth, created_at"),
})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Applicant extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "applicant_id")
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "generation", nullable = false)
	private int generation;

	@Column(name = "age", nullable = false)
	private int age;

	@Enumerated(EnumType.STRING)
	@Column(name = "part", nullable = false)
	private Part part;

	@Column(name = "activity", nullable = false)
	private int activity;

	@Column(name = "growth", nullable = false)
	private int growth;

	@Column(name = "phone_number", nullable = false)
	private String phoneNumber;

	@Column(name = "likes", nullable = false)
	private int likes;

	@Builder
	public Applicant(String name, int generation, int age, Part part, int activity,
		int growth, String phoneNumber) {
		this.name = name;
		this.generation = generation;
		this.age = age;
		this.part = part;
		this.activity = activity;
		this.growth = growth;
		this.phoneNumber = phoneNumber;
		this.likes = DEFAULT_LIKES.getValue();
	}

	public void addLike() {
		this.likes++;
	}
}