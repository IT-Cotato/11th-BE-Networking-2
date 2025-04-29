package cotato.backend.domain.applicant.adapter.in.web.controller.dto.response;

public record ApplicantPreviewResponse(
	Long id, // 지원자 ID
	int generation, // 지원 기수
	String part, // 지원 파트
	int passion, // 참여 적극성
	int growth, // 성장 의지
	int likes, // 받은 좋아요
	String submittedAt // 지원서 제출일
) {

	public static ApplicantPreviewResponse from(ApplicantResponse applicant) {
		return new ApplicantPreviewResponse(
			applicant.id(),
			applicant.generation(),
			applicant.part(),
			applicant.passion(),
			applicant.growth(),
			applicant.likes(),
			applicant.submittedAt()
		);

	}
}
