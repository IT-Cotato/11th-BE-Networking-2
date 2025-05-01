package cotato.backend.application.port;

import cotato.backend.adapter.in.dto.response.DetailFormResponse;
import cotato.backend.application.port.in.LoadDetailFormQuery;
import cotato.backend.application.port.out.LoadFormPort;
import cotato.backend.domain.Form;
import cotato.backend.exception.notFoundInfo.NotFoundFormException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class JpaLoadDetailFormService implements LoadDetailFormQuery {
    private final LoadFormPort loadFormPort;

    @Transactional(readOnly = true)
    public DetailFormResponse load(UUID id) {
        Form form = loadFormPort.findById(id).orElseThrow(NotFoundFormException::construct);
        return DetailFormResponse.of(form);
    }
}
