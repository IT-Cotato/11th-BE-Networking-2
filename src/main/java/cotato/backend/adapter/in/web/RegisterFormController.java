package cotato.backend.adapter.in.web;

import cotato.backend.adapter.in.dto.request.RegisterFormRequest;
import cotato.backend.application.port.in.RegisterFormCommand;
import cotato.backend.common.ApiResponse;
import cotato.backend.common.BaseResponse;
import cotato.backend.common.SuccessDetail;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class RegisterFormController {
    private final RegisterFormCommand registerFormCommand;

    @PostMapping
    public ResponseEntity<BaseResponse<?>> register(@RequestBody @Valid RegisterFormRequest request) {
        return ApiResponse.success(SuccessDetail.REGISTERED, registerFormCommand.register(request));
    }
}
