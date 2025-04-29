package cotato.backend.common.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.experimental.UtilityClass;

@UtilityClass
public class DateTimeFormatUtil {

	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

	public String format(LocalDateTime dateTime) {
		if (dateTime == null) {
			return null;
		}
		return dateTime.format(FORMATTER);
	}
}