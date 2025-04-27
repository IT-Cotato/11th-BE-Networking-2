package cotato.backend.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class PageResponse {
    private int page;
    private int size;
    private int totalPages;
    private long totalElements;
    private List<SortInfo> sortInfos;

    public static PageResponse from(Page page) {

        return new PageResponse(
                page.getNumber(),
                page.getSize(),
                page.getTotalPages(),
                page.getTotalElements(),
                page.getSort()
                        .stream()
                        .map(sort -> new SortInfo(sort.getProperty(), sort.getDirection().name()))
                        .toList()
        );

    }


    private record SortInfo(String property, String direction) {

    }


}
