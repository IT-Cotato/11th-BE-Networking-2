package cotato.backend.adapter.in.web;

import cotato.backend.adapter.in.dto.LoadFormListDto;
import cotato.backend.application.port.in.LoadFormListQuery;
import cotato.backend.common.ApiResponse;
import cotato.backend.common.BaseResponse;
import cotato.backend.common.SortOption;
import cotato.backend.common.SuccessDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class LoadFormListController {
    private final LoadFormListQuery loadFormListQuery;

    @GetMapping
    public ResponseEntity<BaseResponse<?>> load(@RequestParam int pageNumber, @RequestParam SortOption sortOption) {
        return ApiResponse.success(SuccessDetail.RETRIEVED_LIST,
                loadFormListQuery.load(LoadFormListDto.of(pageNumber, sortOption)));
    }
}
