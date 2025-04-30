package cotato.backend.common.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

	// 공통 오류
	BAD_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청입니다.", "COMMON-101"),
	INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "요청 파라미터가 잘못되었습니다.", "COMMON-102"),
	NOT_FOUND(HttpStatus.NOT_FOUND, "찾을 수 없습니다.", "COMMON-103"),
	INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부에서 에러가 발생하였습니다.", "COMMON-104"),

	// 지원서 필수값 누락 오류
	NAME_REQUIRED(HttpStatus.BAD_REQUEST, "이름은 필수 값입니다.", "APPLICATION-201"),
	GENERATION_REQUIRED(HttpStatus.BAD_REQUEST, "지원 기수는 필수 값입니다.", "APPLICATION-202"),
	AGE_REQUIRED(HttpStatus.BAD_REQUEST, "나이는 필수 값입니다.", "APPLICATION-203"),
	PART_REQUIRED(HttpStatus.BAD_REQUEST, "지원 파트는 필수 값입니다.", "APPLICATION-204"),
	PARTICIPATION_SCORE_REQUIRED(HttpStatus.BAD_REQUEST, "참여 적극성은 필수 값입니다.", "APPLICATION-205"),
	GROWTH_MOTIVATION_SCORE_REQUIRED(HttpStatus.BAD_REQUEST, "성장 의지는 필수 값입니다.", "APPLICATION-206"),
	PHONE_NUMBER_REQUIRED(HttpStatus.BAD_REQUEST, "휴대폰 번호는 필수 값입니다.", "APPLICATION-207"),

	// 지원서 유효성 검사 오류
	INVALID_NAME_LENGTH(HttpStatus.BAD_REQUEST, "이름은 2글자 이상 10글자 이하여야 합니다.", "APPLICATION-301"),
	INVALID_GENERATION_RANGE(HttpStatus.BAD_REQUEST, "지원 기수는 1 이상이여야 합니다.", "APPLICATION-302"),
	INVALID_AGE_RANGE(HttpStatus.BAD_REQUEST, "나이는 22세 이상 30세 이하여야 합니다.", "APPLICATION-303"),
	INVALID_SCORE_RANGE(HttpStatus.BAD_REQUEST, "점수는 0점 이상 10점 이하여야 합니다.", "APPLICATION-305"),
	INVALID_PHONE_NUMBER_LENGTH(HttpStatus.BAD_REQUEST, "휴대폰 번호는 11자리 숫자여야 합니다.", "APPLICATION-306"),
	INVALID_PHONE_NUMBER_PREFIX(HttpStatus.BAD_REQUEST, "휴대폰 번호는 010 으로 시작해야 합니다.", "APPLICATION-307"),
	INVALID_PHONE_NUMBER_DIGIT(HttpStatus.BAD_REQUEST, "휴대폰 번호는 숫자만 포함해야 합니다.", "APPLICATION-308"),
	INVALID_PART_VALUE(HttpStatus.BAD_REQUEST, "파트는 PM, DESIGN, FE, BE 중 하나여야 합니다.", "APPLICATION-309"),

	// 지원서 조회/처리 오류
	APPLICATION_NOT_FOUND(HttpStatus.NOT_FOUND, "지원 서류를 찾을 수 없습니다.", "APPLICATION-401"),
	INVALID_APPLICATION_SORT_TYPE(HttpStatus.BAD_REQUEST, "정렬 타입은 OLD, NEW, LIKE_DESC, PARTICIPATION_SCORE_DESC, GROWTH_MOTIVATION_DESC 중 하나여야 합니다.", "APPLICATION-402"),
	;

	private final HttpStatus httpStatus;
	private final String message;
	private final String code;
}