package cotato.backend.api.controller;

import cotato.backend.common.dto.DataResponse;
import cotato.backend.common.dto.PageResponse;
import cotato.backend.common.util.SortUtil;
import cotato.backend.domain.example.application.ApplyService;
import cotato.backend.domain.example.dto.request.ApplyCreateRequest;
import cotato.backend.domain.example.dto.response.ApplyDetailResponse;
import cotato.backend.domain.example.dto.response.ApplyListResponse;
import cotato.backend.domain.example.entity.Apply;
import cotato.backend.domain.example.validator.ApplyValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ApplyController {
    private final ApplyService applyService;

    @PostMapping("/apply")
    public DataResponse<Long> saveApplyById(@RequestBody ApplyCreateRequest applyCreateRequest) {
        ApplyValidator.validateApplyCreateRequest(applyCreateRequest);

        Long id = applyService.saveApply(applyCreateRequest.toEntity());

        return DataResponse.created(id);
    }


    //지원서류 상세 조회
    @GetMapping("/apply/{id}")
    public DataResponse<ApplyDetailResponse> getApplyDetail(@PathVariable("id") Long id) {
        ApplyDetailResponse applyDetailResponse = applyService.findByApplyId(id);
        return DataResponse.from(applyDetailResponse);
        
    }

    //페이지네이션 기본정렬기준 createdAt
    @GetMapping("/apply/lists")
    public DataResponse<List<ApplyListResponse>> getApplyList(@RequestParam(value = "page", defaultValue = "0") int page,
                               @RequestParam(value = "size", defaultValue = "10") int size,
                               @RequestParam(value = "sort", defaultValue = "applyTime") List<String> sort,
                               @RequestParam(value = "direction", defaultValue = "desc") List<String> direction) {

        Sort sortObj = SortUtil.getSort(sort, direction);
        Pageable pageable = PageRequest.of(page, size, sortObj);

        Page<Apply> applyList = applyService.findApplyLists(pageable);

        List<ApplyListResponse> applyListResponses = applyList.map(ApplyListResponse::from).getContent();
        PageResponse pageResponse = PageResponse.from(applyList);

        return DataResponse.from(applyListResponses, pageResponse);
    }

    @PostMapping("/apply/{id}/like")
    public DataResponse<Void> likeApply(@PathVariable("id") Long applyId,
                                        @RequestParam(value = "managerId") Long managerId) {
        applyService.likeApply(applyId, managerId);
        return DataResponse.ok();
    }






}
