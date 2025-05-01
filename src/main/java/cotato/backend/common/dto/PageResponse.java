package cotato.backend.common.dto;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;

import cotato.backend.domain.applicant.enums.PageSort;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PageResponse<T> extends BaseResponse {
	private static final PageSort DEFAULT_PAGE_SORT = PageSort.OLD_SUBMISSION_TIME;

	private final List<T> items;
	private final int currentPage;
	private final int pageSize;
	private final String pageSortBy;
	private final long totalItems;
	private final int totalPages;

	private PageResponse(HttpStatus status, List<T> content, int number, int size, PageSort pageSort,
		long totalElements, int totalPages) {
		super(status);
		this.items = content;
		this.currentPage = number;
		this.pageSize = size;
		this.pageSortBy = pageSort.getDescription();
		this.totalItems = totalElements;
		this.totalPages = totalPages;
	}

	public static <T> PageResponse<T> from(Page<T> page) {
		return from(page, DEFAULT_PAGE_SORT);
	}

	public static <T> PageResponse<T> from(Page<T> page, PageSort pageSort) {
		return new PageResponse<>(
			HttpStatus.OK,
			page.getContent(),
			page.getNumber(),
			page.getSize(),
			pageSort,
			page.getTotalElements(),
			page.getTotalPages()
		);
	}
}