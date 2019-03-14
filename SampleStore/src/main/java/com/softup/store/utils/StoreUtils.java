package com.softup.store.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class StoreUtils {

	@SuppressWarnings("deprecation")
	public static Date deliveryDate(Date date, int num) {

		int day = date.getDate();
		int month = date.getMonth() + 1;
		int year = date.getYear() + 1900;

		String y = Integer.toString(year);

		String m = Integer.toString(month);
		String d = Integer.toString(day);

		String strDate = y + "-" + m + "-" + d;

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();

		try {
			calendar.setTime(simpleDateFormat.parse(strDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		calendar.add(Calendar.DATE, num);
		strDate = simpleDateFormat.format(calendar.getTime());
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date2 = (Date) formatter.parse(strDate);
			return date2;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

}
