package cotato.backend.applicant;

import static cotato.backend.domain.applicant.domain.type.Part.*;
import static org.assertj.core.api.AssertionsForInterfaceTypes.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import cotato.backend.domain.applicant.adapter.in.web.controller.dto.request.ApplicantRequest;
import cotato.backend.domain.applicant.adapter.out.persistence.repository.ApplicantRepository;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
public class RegisterApplicantControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private ApplicantRepository repository;

	private static final String URL = "/api/v1/applicant";
	private static final String VALID_NAME = "김감자"; // 유효한 이름
	private static final String VALID_PHONE = "01012345678"; // 유효한 전화번호
	private static final int VALID_GENERATION = 12; // 유효한 기수
	private static final int VALID_AGE = 25; // 유효한 나이
	private static final int VALID_PASSION = 8; // 유효한 참여 적극성
	private static final int VALID_GROWTH = 9; // 유효한 성장 의지

	@Test
	@DisplayName("지원자 등록 테스트")
	void registerApplicant() throws Exception {
		ApplicantRequest request = new ApplicantRequest(
			VALID_NAME, VALID_GENERATION, VALID_AGE, BACKEND, VALID_PASSION, VALID_GROWTH, VALID_PHONE);

		mockMvc.perform(post(URL)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(request)))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.status").value("Created"));

		assertThat(repository.findAll()).anyMatch(e -> e.getName().equals(VALID_NAME));
	}

	@Test
	@DisplayName("이름이 2글자 미만이면 실패")
	void nameShort() throws Exception {
		assertBadRequest(
			new ApplicantRequest("김", VALID_GENERATION, VALID_AGE, BACKEND, VALID_PASSION, VALID_GROWTH, VALID_PHONE));
	}

	@Test
	@DisplayName("이름이 10글자 초과면 실패")
	void nameLong() throws Exception {
		assertBadRequest(
			new ApplicantRequest("가나다라마바사아자차카타파", VALID_GENERATION, VALID_AGE, BACKEND, VALID_PASSION, VALID_GROWTH,
				VALID_PHONE));
	}

	@Test
	@DisplayName("지원 기수가 0이면 실패")
	void generationInvalid() throws Exception {
		assertBadRequest(
			new ApplicantRequest(VALID_NAME, 0, VALID_AGE, BACKEND, VALID_PASSION, VALID_GROWTH, VALID_PHONE));
	}

	@Test
	@DisplayName("나이가 22세 미만이면 실패")
	void ageYoung() throws Exception {
		assertBadRequest(
			new ApplicantRequest(VALID_NAME, VALID_GENERATION, 21, BACKEND, VALID_PASSION, VALID_GROWTH, VALID_PHONE));
	}

	@Test
	@DisplayName("나이가 30세 초과면 실패")
	void ageOld() throws Exception {
		assertBadRequest(
			new ApplicantRequest(VALID_NAME, VALID_GENERATION, 31, BACKEND, VALID_PASSION, VALID_GROWTH, VALID_PHONE));
	}

	@Test
	@DisplayName("참여 점수가 10점 초과면 실패")
	void passionInvalid() throws Exception {
		assertBadRequest(
			new ApplicantRequest(VALID_NAME, VALID_GENERATION, VALID_AGE, BACKEND, 11, VALID_GROWTH, VALID_PHONE));
	}

	@Test
	@DisplayName("성장 점수가 0점 미만이면 실패")
	void growthInvalid() throws Exception {
		assertBadRequest(
			new ApplicantRequest(VALID_NAME, VALID_GENERATION, VALID_AGE, BACKEND, VALID_PASSION, -1, VALID_PHONE));
	}

	@Test
	@DisplayName("전화번호가 010으로 시작하지 않으면 실패")
	void phoneInvalidPrefix() throws Exception {
		assertBadRequest(
			new ApplicantRequest(VALID_NAME, VALID_GENERATION, VALID_AGE, BACKEND, VALID_PASSION, VALID_GROWTH,
				"01112345678"));
	}

	@Test
	@DisplayName("전화번호가 11자리가 아니면 실패")
	void phoneInvalidLength() throws Exception {
		assertBadRequest(
			new ApplicantRequest(VALID_NAME, VALID_GENERATION, VALID_AGE, BACKEND, VALID_PASSION, VALID_GROWTH,
				"0101234"));
	}

	private void assertBadRequest(ApplicantRequest request) throws Exception {
		mockMvc.perform(post(URL)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(request)))
			.andExpect(status().isBadRequest());
	}
}
