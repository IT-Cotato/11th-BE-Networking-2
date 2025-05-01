package cotato.backend.application.port.in;

import cotato.backend.adapter.in.dto.request.RegisterFormRequest;
import cotato.backend.adapter.in.dto.response.RegisterFormResponse;

public interface RegisterFormCommand {
    RegisterFormResponse register(RegisterFormRequest request);
}
