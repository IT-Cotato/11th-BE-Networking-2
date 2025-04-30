package cotato.backend.domain.applicant.domain;

import lombok.Getter;

@Getter
public class ApplicantLike {

	private final Long id;
	private final Long applicantId;

	private ApplicantLike(Long id, Long applicantId) {
		this.id = id;
		this.applicantId = applicantId;
	}

	public static ApplicantLike createNew(Long applicantId) {
		return new ApplicantLike(null, applicantId);
	}

	public static ApplicantLike reconstitute(Long id, Long applicantId) {
		return new ApplicantLike(id, applicantId);
	}
}