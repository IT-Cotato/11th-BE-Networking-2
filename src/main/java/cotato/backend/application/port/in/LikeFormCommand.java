package cotato.backend.application.port.in;

import cotato.backend.adapter.in.dto.LikeFormDto;
import org.springframework.stereotype.Service;

@Service
public interface LikeFormCommand {
    void like(LikeFormDto dto);
}
