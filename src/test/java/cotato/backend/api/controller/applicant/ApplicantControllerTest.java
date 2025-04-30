package cotato.backend.api.controller.applicant;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import cotato.backend.api.applicant.controller.ApplicantController;
import cotato.backend.api.applicant.dto.request.ApplicantRequest;
import cotato.backend.domain.applicant.application.ApplicantService;

@WebMvcTest(ApplicantController.class)
class ApplicantControllerTest {

	@InjectMocks
	private ApplicantController applicantController;

	@MockitoBean
	private ApplicantService applicantService;

	@Autowired
	private MockMvc mockMvc;

	private final ObjectMapper objectMapper = new ObjectMapper();

	@Test
	@DisplayName("지원자 등록 요청 성공")
	public void createApplicant_ValidRequest_ShouldReturn201() throws Exception {
		// given
		ApplicantRequest request = new ApplicantRequest(
			"이름",
			5L,
			25L,
			"BE",
			8L,
			9L,
			"01099999999"
		);

		String json = objectMapper.writeValueAsString(request);

		// when & then
		mockMvc.perform(post("/api/applicants")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
			.andExpect(status().isCreated());
	}

	@ParameterizedTest(name = "[{index}] {0}")
	@DisplayName("지원자 등록 요청 실패 - 잘못된 요청")
	@MethodSource("invalidCreateApplicantRequest")
	public void createApplicant_InvalidRequest_ShouldReturn400(ApplicantRequest request,
		String expectedErrorMessage) throws Exception {
		// given
		String json = objectMapper.writeValueAsString(request);

		// when & then
		mockMvc.perform(post("/api/applicants")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.reason[0]").value(expectedErrorMessage));
	}

	static Stream<Arguments> invalidCreateApplicantRequest() {
		return Stream.of(
			// 이름 필드 검증
			Arguments.of(
				new ApplicantRequest(
					null,
					5L,
					25L,
					"BE",
					8L,
					9L,
					"01099999999"
				),
				"이름은 필수입니다."
			),
			Arguments.of(
				new ApplicantRequest(
					"가",
					5L,
					25L,
					"BE",
					8L,
					9L,
					"01099999999"
				),
				"이름은 2글자 이상 10글자 이하여야 합니다."
			),
			Arguments.of(
				new ApplicantRequest(
					"가나다라마바사아자차카타",
					5L,
					25L,
					"BE",
					8L,
					9L,
					"01099999999"
				),
				"이름은 2글자 이상 10글자 이하여야 합니다."
			),
			Arguments.of(
				new ApplicantRequest(
					"John",
					5L,
					25L,
					"BE",
					8L,
					9L,
					"01099999999"
				),
				"이름은 한글만 입력 가능합니다."
			),

			// 지원 기수 필드 검증
			Arguments.of(
				new ApplicantRequest(
					"홍길동",
					null,
					25L,
					"BE",
					8L,
					9L,
					"01099999999"
				),
				"지원 기수는 필수입니다."
			),
			Arguments.of(
				new ApplicantRequest(
					"홍길동",
					0L,
					25L,
					"BE",
					8L,
					9L,
					"01099999999"
				),
				"지원 기수는 1 이상이어야 합니다."
			),

			// 나이 필드 검증
			Arguments.of(
				new ApplicantRequest(
					"홍길동",
					5L,
					null,
					"BE",
					8L,
					9L,
					"01099999999"
				),
				"나이는 필수입니다."
			),
			Arguments.of(
				new ApplicantRequest(
					"홍길동",
					5L,
					21L,
					"BE",
					8L,
					9L,
					"01099999999"
				),
				"나이는 22세 이상이어야 합니다."
			),
			Arguments.of(
				new ApplicantRequest(
					"홍길동",
					5L,
					31L,
					"BE",
					8L,
					9L,
					"01099999999"
				),
				"나이는 30세 이하여야 합니다."
			),

			// 파트 필드 검증
			Arguments.of(
				new ApplicantRequest(
					"홍길동",
					5L,
					25L,
					null,
					8L,
					9L,
					"01099999999"
				),
				"파트는 필수입니다."
			),
			Arguments.of(
				new ApplicantRequest(
					"홍길동",
					5L,
					25L,
					"QA",
					8L,
					9L,
					"01099999999"
				),
				"파트는 BE, FE, PM, DE 중 하나여야 합니다."
			),

			// 참여 적극성 필드 검증
			Arguments.of(
				new ApplicantRequest(
					"홍길동",
					5L,
					25L,
					"BE",
					null,
					9L,
					"01099999999"
				),
				"참여 적극성은 필수입니다."
			),
			Arguments.of(
				new ApplicantRequest(
					"홍길동",
					5L,
					25L,
					"BE",
					-1L,
					9L,
					"01099999999"
				),
				"참여 적극성은 0 이상이어야 합니다."
			),
			Arguments.of(
				new ApplicantRequest(
					"홍길동",
					5L,
					25L,
					"BE",
					11L,
					9L,
					"01099999999"
				),
				"참여 적극성은 10 이하여야 합니다."
			),

			// 성장 의지 필드 검증
			Arguments.of(
				new ApplicantRequest(
					"홍길동",
					5L,
					25L,
					"BE",
					8L,
					null,
					"01099999999"
				),
				"성장 의지는 필수입니다."
			),
			Arguments.of(
				new ApplicantRequest(
					"홍길동",
					5L,
					25L,
					"BE",
					8L,
					-1L,
					"01099999999"
				),
				"성장 의지는 0 이상이어야 합니다."
			),
			Arguments.of(
				new ApplicantRequest(
					"홍길동",
					5L,
					25L,
					"BE",
					8L,
					11L,
					"01099999999"
				),
				"성장 의지는 10 이하여야 합니다."
			),

			// 휴대폰 번호 필드 검증
			Arguments.of(
				new ApplicantRequest(
					"홍길동",
					5L,
					25L,
					"BE",
					8L,
					9L,
					null
				),
				"휴대폰 번호는 필수입니다."
			),
			Arguments.of(
				new ApplicantRequest(
					"홍길동",
					5L,
					25L,
					"BE",
					8L,
					9L,
					"01512345678"
				),
				"휴대폰 번호는 010으로 시작하는 11자리 숫자여야 합니다."
			),
			Arguments.of(
				new ApplicantRequest(
					"홍길동",
					5L,
					25L,
					"BE",
					8L,
					9L,
					"0101234567"
				),
				"휴대폰 번호는 010으로 시작하는 11자리 숫자여야 합니다."
			),
			Arguments.of(
				new ApplicantRequest(
					"홍길동",
					5L,
					25L,
					"BE",
					8L,
					9L,
					"0109999999a"
				),
				"휴대폰 번호는 010으로 시작하는 11자리 숫자여야 합니다."
			)
		);
	}
}