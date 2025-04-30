package cotato.backend.domain.applicant.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cotato.backend.common.dto.DataResponse;
import cotato.backend.domain.applicant.dto.request.ApplicantRequestDto;
import cotato.backend.domain.applicant.dto.response.EnrollResponseDto;
import cotato.backend.domain.applicant.dto.response.SearchDetailsResponseDto;
import cotato.backend.domain.applicant.service.command.ApplicantCommandService;
import cotato.backend.domain.applicant.service.query.ApplicantQueryService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@RequestMapping("/api/v1/applicant")
public class ApplicantController {

	private final ApplicantCommandService commandService;
	private final ApplicantQueryService queryService;

	@PostMapping
	public ResponseEntity<DataResponse<EnrollResponseDto>> save(@Valid @RequestBody ApplicantRequestDto request) {
		EnrollResponseDto response = commandService.save(request);
		return ResponseEntity.ok(DataResponse.created(response));
	}

	@GetMapping("/{id}")
	public ResponseEntity<DataResponse<SearchDetailsResponseDto>> findById(@PathVariable Long id) {
		SearchDetailsResponseDto response = queryService.findById(id);
		return ResponseEntity.ok(DataResponse.from(response));
	}
}
