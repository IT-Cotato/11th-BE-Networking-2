package cotato.backend.common.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

	// Common
	USER_INPUT_EXCEPTION(HttpStatus.BAD_REQUEST, "C-001", "사용자 입력 오류"),

	// Applicant
	APPLICANT_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "A-001", "해당 지원자를 찾을 수 없습니다."),

	// Server
	INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "S-001", "서버 내부에서 에러가 발생하였습니다."),
	;

	private final HttpStatus status;
	private final String code;
	private final String message;
}