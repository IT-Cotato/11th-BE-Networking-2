package cotato.backend.common.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

	// Application Error
	APPLICANT_NOT_FOUND(HttpStatus.NOT_FOUND, "지원자를 찾을 수 없습니다.", "APPLICANT-001"),
	APPLICANT_INVALID_NAME(HttpStatus.BAD_REQUEST, "지원자 이름이 유효하지 않습니다.", "APPLICANT-002"),
	APPLICANT_INVALID_GENERATION(HttpStatus.BAD_REQUEST, "지원 기수가 유효하지 않습니다.", "APPLICANT-003"),
	APPLICANT_INVALID_AGE(HttpStatus.BAD_REQUEST, "지원자 나이가 유효하지 않습니다.", "APPLICANT-004"),
	APPLICANT_INVALID_PASSION(HttpStatus.BAD_REQUEST, "지원자 참여 적극성이 유효하지 않습니다.", "APPLICANT-006"),
	APPLICANT_INVALID_GROWTH(HttpStatus.BAD_REQUEST, "지원자 성장 의지가 유효하지 않습니다.", "APPLICANT-007"),
	APPLICANT_INVALID_PHONE(HttpStatus.BAD_REQUEST, "지원자 전화번호가 유효하지 않습니다.", "APPLICANT-008"),

	//400
	BAD_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청입니다.", "COMMON-001"),
	INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "요청 파라미터가 잘못되었습니다.", "COMMON-002"),
	NOT_FOUND(HttpStatus.NOT_FOUND, "찾을 수 없습니다.", "COMMON-003"),

	//500
	INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부에서 에러가 발생하였습니다.", "COMMON-004"),
	;

	private final HttpStatus httpStatus;
	private final String message;
	private final String code;
}