package cotato.backend.adapter.in.web;

import cotato.backend.application.port.in.LoadDetailFormQuery;
import cotato.backend.common.ApiResponse;
import cotato.backend.common.BaseResponse;
import cotato.backend.common.SuccessDetail;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class LoadDetailFormController {
    private final LoadDetailFormQuery loadDetailFormQuery;

    @GetMapping("/{formId}")
    public ResponseEntity<BaseResponse<?>> load(@PathVariable UUID formId) {
        return ApiResponse.success(SuccessDetail.RETRIEVED_DETAIL, loadDetailFormQuery.load(formId));
    }
}
