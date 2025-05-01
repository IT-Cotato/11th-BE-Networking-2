package cotato.backend.adapter.in.web;

import cotato.backend.adapter.in.dto.LikeFormDto;
import cotato.backend.adapter.in.dto.request.LikeFormRequest;
import cotato.backend.application.port.in.LikeFormCommand;
import cotato.backend.common.ApiResponse;
import cotato.backend.common.BaseResponse;
import cotato.backend.common.SuccessDetail;
import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class LikeFormController {
    private final LikeFormCommand likeFormCommand;

    @PostMapping("/{formId}")
    public ResponseEntity<BaseResponse<?>> like(@RequestBody @Valid LikeFormRequest request,
                                                @PathVariable UUID formId) {
        likeFormCommand.like(LikeFormDto.of(request, formId));
        return ApiResponse.success(SuccessDetail.LIKED);
    }
}
