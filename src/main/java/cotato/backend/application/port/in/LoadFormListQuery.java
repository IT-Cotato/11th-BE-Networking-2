package cotato.backend.application.port.in;

import cotato.backend.adapter.in.dto.LoadFormListDto;
import cotato.backend.adapter.in.dto.response.FormListResponse;

public interface LoadFormListQuery {
    FormListResponse load(LoadFormListDto dto);
}
