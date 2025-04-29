package cotato.backend.applicant;

import static cotato.backend.domain.applicant.domain.type.Part.*;
import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
@ActiveProfiles("test")
@Transactional
public class UpdateApplicantControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ApplicantRepository repository;

	private static final String BASE_URL = "/api/v1/applicant";
	private Long applicantId;

	@BeforeEach
	void setUp() throws Exception {
		ApplicantJpaEntity saved = repository.save(ApplicantJpaEntity.from(Applicant.withoutId(
			"지원자", 11, 22, BACKEND, 1, 2, "01022223333")));

		applicantId = saved.getId();
	}

	@Test
	@DisplayName("좋아요 요청 시 count 증가")
	void like() throws Exception {
		mockMvc.perform(post(BASE_URL + "/" + applicantId + "/like"))
			.andExpect(status().isOk());

		assertThat(repository.findById(applicantId).orElseThrow().getLikes()).isEqualTo(1);
	}
}

