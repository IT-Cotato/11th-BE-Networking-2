package cotato.backend.domain.example.dto.response;

import java.util.List;
import org.springframework.data.domain.Page;
import lombok.Getter;

@Getter
public class PageResponse<T> {
    private final List<T> content;
    private final int pageNumber;
    private final int pageSize;
    private final long totalElements;
    private final int totalPages;
    private final String sortBy;

    public PageResponse(Page<T> page, String sortBy) {
        this.content = page.getContent();
        this.pageNumber = page.getNumber();
        this.pageSize = page.getSize();
        this.totalElements = page.getTotalElements();
        this.totalPages = page.getTotalPages();
        this.sortBy = sortBy;
    }
}
