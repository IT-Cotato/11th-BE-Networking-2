package cotato.backend.domain.applicant.entity;

import cotato.backend.domain.applicant.domain.ApplicantLike;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "applicant_like")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApplicantLikeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@Column(name = "applicant_id", nullable = false)
	Long applicantId;

	@Builder
	public ApplicantLikeEntity(Long id, Long applicantId) {
		this.id = id;
		this.applicantId = applicantId;
	}

	public static ApplicantLikeEntity fromDomain(ApplicantLike applicantLike) {
		return ApplicantLikeEntity.builder()
			.applicantId(applicantLike.getApplicantId())
			.build();
	}

	public ApplicantLike toDomain() {
		return ApplicantLike.reconstitute(id, applicantId);
	}
}