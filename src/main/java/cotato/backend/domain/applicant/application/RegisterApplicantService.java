package cotato.backend.domain.applicant.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cotato.backend.domain.applicant.adapter.in.web.controller.dto.response.ApplicantResponse;
import cotato.backend.domain.applicant.application.command.RegisterApplicantCommand;
import cotato.backend.domain.applicant.application.port.in.RegisterApplicantUseCase;
import cotato.backend.domain.applicant.application.port.out.SaveApplicantPort;
import cotato.backend.domain.applicant.domain.model.Applicant;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class RegisterApplicantService implements RegisterApplicantUseCase {

	private final SaveApplicantPort saveApplicantPort;

	@Override
	public ApplicantResponse register(final RegisterApplicantCommand command) {

		Applicant applicant = Applicant.withoutId(
			command.getName(),
			command.getGeneration(),
			command.getAge(),
			command.getPart(),
			command.getPassion(),
			command.getGrowth(),
			command.getPhone()
		);

		Applicant savedApplicant = saveApplicantPort.save(applicant);
		return ApplicantResponse.from(savedApplicant);
	}
}
