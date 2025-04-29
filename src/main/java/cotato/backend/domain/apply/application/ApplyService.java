package cotato.backend.domain.apply.application;

import cotato.backend.domain.apply.converter.ApplyConverter;
import cotato.backend.domain.apply.dao.ApplyRepository;
import cotato.backend.domain.apply.dto.request.ApplyCreateRequest;
import cotato.backend.domain.apply.dto.response.ApplyCreateResponse;
import cotato.backend.domain.apply.entity.Apply;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplyService {

    private final ApplyRepository applyRepository;

    public ApplyCreateResponse createApply(ApplyCreateRequest applyCreateRequest) {

        //중복 지원자 검증 필요?

        Apply apply = ApplyConverter.toApply(applyCreateRequest);
        applyRepository.save(apply);

        ApplyCreateResponse applyCreateResponse = ApplyConverter.toApplyCreateResponse(apply);

        return applyCreateResponse;
    }
}
