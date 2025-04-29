package cotato.backend.domain.applicant.application.command;

import cotato.backend.domain.applicant.adapter.in.web.controller.dto.request.ApplicantRequest;
import cotato.backend.domain.applicant.domain.type.Part;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RegisterApplicantCommand {

	private final String name;
	private final int generation;
	private final int age;
	private final Part part;
	private final int passion;
	private final int growth;
	private final String phone;

	public static RegisterApplicantCommand from(ApplicantRequest request) {
		return new RegisterApplicantCommand(
			request.name(),
			request.generation(),
			request.age(),
			request.part(),
			request.passion(),
			request.growth(),
			request.phone()
		);
	}

}
