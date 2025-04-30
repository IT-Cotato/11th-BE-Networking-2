package cotato.backend.domain.apply.application;

import cotato.backend.common.exception.AppException;
import cotato.backend.common.exception.EntityNotFoundException;
import cotato.backend.common.exception.ErrorCode;
import cotato.backend.domain.apply.converter.ApplyConverter;
import cotato.backend.domain.apply.dao.ApplyRepository;
import cotato.backend.domain.apply.dto.request.ApplyCreateRequest;
import cotato.backend.domain.apply.dto.response.ApplyCreateResponse;
import cotato.backend.domain.apply.dto.response.ApplyDetailResponse;
import cotato.backend.domain.apply.dto.response.ApplyPageResponse;
import cotato.backend.domain.apply.dto.response.ApplySummaryResponse;
import cotato.backend.domain.apply.entity.Apply;
import cotato.backend.domain.apply.util.ApplySortMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ApplyService {

    private final ApplyRepository applyRepository;

    public ApplyCreateResponse createApply(ApplyCreateRequest applyCreateRequest) {
        //중복 지원자 검증
        if(applyRepository.existsByPhoneNumber(applyCreateRequest.getPhoneNumber())&&applyRepository.existsByName(applyCreateRequest.getName())) {
            throw new AppException(ErrorCode.ALREADY_EXIST_APPLICANT);
        }

        Apply apply = ApplyConverter.toApply(applyCreateRequest);
        applyRepository.save(apply);
        return ApplyConverter.toApplyCreateResponse(apply);
    }

    public String postApplyLike(Long apply_id){

        Apply apply = applyRepository.findById(apply_id)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.NOT_FOUND_APPLY));

        apply.addLike();
        return "좋아요를 눌렀습니다.";
    }

    @Transactional(readOnly = true)
    public ApplyPageResponse getFilteredApplies(String sort, Pageable pageable) {
        Sort sortOrder = ApplySortMapper.getSort(sort);
        Pageable pageableWithSort = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sortOrder);

        Page<Apply> applies = applyRepository.findAll(pageableWithSort);
        Page<ApplySummaryResponse> mappedPage = applies.map(ApplySummaryResponse::from);
        return ApplyPageResponse.from(mappedPage, sort);
    }

    public ApplyDetailResponse getApplyDetail(Long applyId) {
        Apply apply = applyRepository.findById(applyId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.NOT_FOUND_APPLY));

        return ApplyDetailResponse.from(apply);
    }

}
