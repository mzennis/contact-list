package id.mzennis.contact.helper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by mzennis on 9/13/16.
 */
public class DateHelper {

	public static final String SET_TIMEZONE = "Asia/Jakarta";

	public static final String[] MONTHS = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};

	public DateHelper(){}

	public static int day() {
		return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
	}

	public static int month() {
		return Calendar.getInstance().get(Calendar.MONTH) + 1;
	}

	public static int year() {
		return Calendar.getInstance().get(Calendar.YEAR);
	}

	public static long timestamp(){
		return System.currentTimeMillis() / 1000;
	}

	public static Date date(){
		final Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(System.currentTimeMillis());
		return cal.getTime();
	}

	public static String now(){
		Calendar c = Calendar.getInstance();
		SimpleDateFormat df = dateFormat("yyyy-MM-dd");
		return df.format(c.getTime());
	}

	public static SimpleDateFormat dateFormat(String format){
		return new SimpleDateFormat(format, Locale.getDefault());
	}

}
