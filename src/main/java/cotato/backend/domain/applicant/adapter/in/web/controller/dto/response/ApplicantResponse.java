package cotato.backend.domain.applicant.adapter.in.web.controller.dto.response;

import cotato.backend.common.util.DateTimeFormatUtil;
import cotato.backend.domain.applicant.domain.model.Applicant;

public record ApplicantResponse(
	Long id,    // 지원자 ID
	int generation,    // 지원 기수
	String part,   // 지원 파트
	int passion,   // 참여 적극성
	int growth,  // 성장 의지
	int likes,  // 받은 좋아요
	String phone,  // 전화번호
	String submittedAt // 지원서 제출일, yyyy-MM-dd HH:mm
) {
	public static ApplicantResponse from(
		Applicant applicant
	) {
		return new ApplicantResponse(
			applicant.getId(),
			applicant.getGeneration(),
			applicant.getPart().getPartName(),
			applicant.getPassion(),
			applicant.getGrowth(),
			applicant.getLikes(),
			applicant.getPhone(),
			DateTimeFormatUtil.format(applicant.getSubmittedAt())
		);
	}
}
