package cotato.backend.common.dto;

import java.util.List;

import org.springframework.data.domain.Page;

import cotato.backend.domain.application.enums.SortType;

public record PageResponse<T>(
	List<T> content,
	int page,
	int size,
	SortType sortType,
	long totalElements,
	int totalPages
) {
	public static <T> PageResponse<T> of(Page<T> page, SortType sortType) {
		return new PageResponse<>(
			page.getContent(),
			page.getNumber(),
			page.getSize(),
			sortType,
			page.getTotalElements(),
			page.getTotalPages()
		);
	}
}
