package cotato.backend.api.controller;

import cotato.backend.common.dto.DataResponse;
import cotato.backend.common.dto.PageResponse;
import cotato.backend.common.util.SortUtil;
import cotato.backend.domain.example.application.ApplyService;
import cotato.backend.domain.example.dto.info.ApplyLikeInfo;
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

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    //페이지네이션
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

    @GetMapping("/apply/lists/orderByLike")
    public DataResponse<List<ApplyListResponse>> getApplyListOrderByLike(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size, Sort sort) {

        //좋아요 역순, 지원서류 역순
        Sort sortObj = Sort.by("likeCount").descending();
        sortObj.and(Sort.by("applyTime").descending());
        Pageable pageable = PageRequest.of(page, size, sortObj);

        //size + 10개 applyLikeInfo가져오기
        int tempSize = size+10;
        List<ApplyLikeInfo> applyList = applyService.findApplyListsOrderByLike(page, tempSize);

        //applyId만 가져오기
        List<Long> applyIds = applyList.stream()
                .map(ApplyLikeInfo::getApplyId)
                .toList();

        //applyId로 size + 10개 지원서류 가져오기
        List<ApplyListResponse> applyListResponses = new ArrayList<>(applyService.findApplyLists(applyIds));

        //좋아요 개수 map에 저장
        Map<Long, Long> applyIdToLikeCountMap = applyList.stream()
                .collect(Collectors.toMap(ApplyLikeInfo::getApplyId, ApplyLikeInfo::getLikeCount));
        
        //좋아요 개수 설정
        applyListResponses.forEach(response -> {
            Long likeCount = applyIdToLikeCountMap.get(response.getApplyId());
            response.setLikeCount(likeCount);
        });


        //likecount 내림차순, applytime 내림차순
        applyListResponses.sort(Comparator
                .comparing(ApplyListResponse::getLikeCount, Comparator.nullsLast(Comparator.reverseOrder()))
                .thenComparing(ApplyListResponse::getApplyTime, Comparator.nullsLast(Comparator.reverseOrder())));


        //size + 10개에서 size개수만큼 자르기 sublist
        List<ApplyListResponse> subList = applyListResponses.subList(0, Math.min(size, applyListResponses.size()));

        return DataResponse.from(applyListResponses);
    }

    @PostMapping("/apply/{id}/like")
    public DataResponse<Void> likeApply(@PathVariable("id") Long applyId,
                                        @RequestParam(value = "managerId") Long managerId) {
        applyService.likeApply(applyId, managerId);
        return DataResponse.ok();
    }






}
