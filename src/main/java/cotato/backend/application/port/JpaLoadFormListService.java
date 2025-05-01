package cotato.backend.application.port;

import cotato.backend.adapter.in.dto.LoadFormListDto;
import cotato.backend.adapter.in.dto.response.FormListResponse;
import cotato.backend.adapter.in.dto.response.FormResponse;
import cotato.backend.adapter.in.dto.response.PaginationInfoResponse;
import cotato.backend.application.port.in.LoadFormListQuery;
import cotato.backend.application.port.out.LoadFormListByLikePort;
import cotato.backend.application.port.out.LoadFormListPort;
import cotato.backend.domain.Form;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class JpaLoadFormListService implements LoadFormListQuery {
    public final int PAGE_SIZE = 10;
    private final LoadFormListPort loadFormListPort;
    private final LoadFormListByLikePort loadFormListByLikePort;

    @Transactional(readOnly = true)
    public FormListResponse load(LoadFormListDto dto) {
        Pageable pageable = PageRequest.of(dto.pageNumber(), PAGE_SIZE);
        Page<Form> page = switch (dto.sortOption()) {
            case OLDEST -> loadFormListPort.findAllByOrderBySubmittedAtDesc(pageable);
            case LATEST -> loadFormListPort.findAllByOrderBySubmittedAtAsc(pageable);
            case MOST_LIKE -> loadFormListByLikePort.findFormListByLike(pageable);
            case HIGHEST_ENGAGEMENT -> loadFormListPort.findAllByOrderByEngagementDescSubmittedAtDesc(pageable);
            case HIGHEST_GROWTH_DESIRE -> loadFormListPort.findAllByOrderByGrowthDesireDescSubmittedAtDesc(pageable);
        };
        return FormListResponse.of(page.stream().map(FormResponse::of).toList(),
                PaginationInfoResponse.of(dto.pageNumber(), PAGE_SIZE, dto.sortOption(), page.getNumberOfElements(),
                        page.getTotalPages()));
    }
}
