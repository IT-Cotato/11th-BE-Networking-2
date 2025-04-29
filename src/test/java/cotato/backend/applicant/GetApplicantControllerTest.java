package cotato.backend.applicant;

import static cotato.backend.domain.applicant.domain.type.Part.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import cotato.backend.domain.applicant.adapter.out.persistence.entity.ApplicantJpaEntity;
import cotato.backend.domain.applicant.adapter.out.persistence.repository.ApplicantRepository;
import cotato.backend.domain.applicant.domain.model.Applicant;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
public class GetApplicantControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ApplicantRepository repository;

	private static final String BASE_URL = "/api/v1/applicant";
	private Long topApplicantId;

	@BeforeEach
	void setUp() {
		// 지원자 15명 생성 및 저장
		List<ApplicantJpaEntity> saved = IntStream.rangeClosed(1, 15)
			.mapToObj(i -> {
				Applicant applicant = Applicant.withoutId(
					"지원자" + i,
					11 + (i % 3),
					22 + (i % 5),
					BACKEND,
					i % 11,
					10 - (i % 11),
					"0100000" + String.format("%04d", i)
				);
				return ApplicantJpaEntity.from(applicant);
			})
			.map(repository::save)
			.toList();

		topApplicantId = saved.get(0).getId();
	}

	@Test
	@DisplayName("지원자 목록 조회 - 오래된 순, 페이징 적용")
	void getApplicantListWithPaging() throws Exception {
		mockMvc.perform(get(BASE_URL)
				.param("applicantSortType", "SUBMITTED_ASC")
				.param("page", "0"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.data.applicants.length()").value(10))
			.andExpect(jsonPath("$.data.totalElements").value(15));
	}

	@Test
	@DisplayName("지원자 목록 조회 - 좋아요 내림차순")
	void getApplicantListSortedByLikes() throws Exception {
		for (int i = 0; i < 5; i++) {
			mockMvc.perform(post(BASE_URL + "/" + topApplicantId + "/like"));
		}

		mockMvc.perform(get(BASE_URL)
				.param("applicantSortType", "LIKES_DESC")
				.param("page", "0"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.data.applicants[0].likes").value(5));
	}

	@Test
	@DisplayName("지원자 단건 조회 성공")
	void getApplicantDetail() throws Exception {
		mockMvc.perform(get(BASE_URL + "/" + topApplicantId))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.data.id").value(topApplicantId))
			.andExpect(jsonPath("$.data.phone").exists());
	}
}
