package cotato.backend.common.dto;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DataResponse<T> extends BaseResponse {

	private final T data;
	private final PageResponse pageResponse;

	private DataResponse(HttpStatus status, T data, PageResponse pageResponse) {
		super(status);
		this.data = data;
        this.pageResponse = pageResponse;
    }

	private DataResponse(HttpStatus status, T data) {
		super(status);
		this.data = data;
		this.pageResponse = null;
	}

	public static <T> DataResponse<T> from(T data) {
		return new DataResponse<>(HttpStatus.OK, data);
	}

	public static<T> DataResponse<T> from(T data, Page<?> page) {
		return new DataResponse<>(HttpStatus.OK, data);
	}

	public static<T> DataResponse<T> from(T data, PageResponse pageResponse) {
		return new DataResponse<>(HttpStatus.OK, data, pageResponse);
	}

	public static <T> DataResponse<Void> ok() {
		return new DataResponse<>(HttpStatus.OK, null);
	}

	public static <T> DataResponse<T> created(T data) {
		return new DataResponse<>(HttpStatus.CREATED, data);
	}

	// 다른 응답 필요하면 추가
}