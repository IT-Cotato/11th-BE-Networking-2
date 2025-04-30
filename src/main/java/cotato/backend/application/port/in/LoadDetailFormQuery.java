package cotato.backend.application.port.in;

import cotato.backend.adapter.in.dto.response.DetailFormResponse;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public interface LoadDetailFormQuery {
    DetailFormResponse load(UUID id);
}
