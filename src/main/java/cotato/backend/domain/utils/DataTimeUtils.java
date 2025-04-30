package cotato.backend.domain.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import cotato.backend.common.exception.AppException;
import cotato.backend.common.exception.ErrorCode;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DataTimeUtils {

	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

	public static String format(LocalDateTime dateTime) {
		return Optional.ofNullable(dateTime)
			.map(dt -> dt.format(formatter))
			.orElseThrow(() -> new AppException(ErrorCode.DATE_TIME_FORMAT_ERROR));
	}
}