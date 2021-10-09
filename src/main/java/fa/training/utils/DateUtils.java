package fa.training.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	public static Date addTime(Date date, int hour) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, hour);
		return calendar.getTime();
	}
}
