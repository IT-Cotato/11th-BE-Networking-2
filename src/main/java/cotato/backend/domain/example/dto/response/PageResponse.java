package cotato.backend.domain.example.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@AllArgsConstructor
public class PageResponse<T> {

    private List<T> applicants;
    private int PageNumber;
    private int pageSize;
    private String sort;
    private long totalElements;
    private int totalPages;

    public static <T> PageResponse<T> from(Page<T> page, String sort) {
        return new PageResponse<> (
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                sort,
                page.getTotalElements(),
                page.getTotalPages()
        );
    }
}
