package cotato.backend.domain.application.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cotato.backend.domain.application.dao.ApplicationRepository;
import cotato.backend.domain.application.entity.ApplicationEntity;
import cotato.backend.domain.example.entity.Part;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ApplicationService {

	private final ApplicationRepository applicationRepository;

	@Transactional
	public Long apply(
		String name,
		Integer generation,
		Integer age,
		Part part,
		Integer participationScore,
		Integer growthMotivation,
		String phoneNumber
	) {
		ApplicationEntity applicationEntity = ApplicationEntity.builder()
			.name(name)
			.generation(generation)
			.age(age)
			.part(part)
			.participationScore(participationScore)
			.growthMotivation(growthMotivation)
			.phoneNumber(phoneNumber)
			.build();

		ApplicationEntity savedEntity = applicationRepository.save(applicationEntity);
		return savedEntity.getId();
	}
}
