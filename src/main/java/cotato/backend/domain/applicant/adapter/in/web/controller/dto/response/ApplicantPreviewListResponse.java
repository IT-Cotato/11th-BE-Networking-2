package cotato.backend.domain.applicant.adapter.in.web.controller.dto.response;

import java.util.List;

import org.springframework.data.domain.Page;

public record ApplicantPreviewListResponse(
	int page, // 현재 페이지
	int size, // 페이지 크기
	String sort, // 정렬 기준
	long totalElements, // 전체 요소 수
	int totalPages, // 전체 페이지 수
	List<ApplicantPreviewResponse> applicants // 지원자 미리보기 리스트
) {

	public static ApplicantPreviewListResponse from(Page<ApplicantResponse> applicants) {
		return new ApplicantPreviewListResponse(
			applicants.getNumber(), // 현재 페이지
			applicants.getSize(), // 페이지 크기
			applicants.getSort().toString(), // 정렬 기준
			applicants.getTotalElements(), // 전체 요소 수
			applicants.getTotalPages(),            // 전체 페이지 수
			applicants.stream()
				.map(ApplicantPreviewResponse::from) // 지원자 미리보기 리스트
				.toList()
		);
	}
}
