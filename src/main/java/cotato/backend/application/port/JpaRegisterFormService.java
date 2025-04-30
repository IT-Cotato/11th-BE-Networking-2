package cotato.backend.application.port;

import cotato.backend.adapter.in.dto.request.RegisterFormRequest;
import cotato.backend.adapter.in.dto.response.RegisterFormResponse;
import cotato.backend.application.port.in.RegisterFormCommand;
import cotato.backend.application.port.out.RegisterFormPort;
import cotato.backend.domain.Form;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class JpaRegisterFormService implements RegisterFormCommand {
    private final RegisterFormPort registerFormPort;

    @Transactional
    public RegisterFormResponse register(RegisterFormRequest request) {
        Form form = registerFormPort.save(
                new Form(request.name(), request.generation(), request.age(), request.part(), request.engagement(),
                        request.growthDesire(), request.phoneNumber()));
        return RegisterFormResponse.of(form.getId());
    }
}
