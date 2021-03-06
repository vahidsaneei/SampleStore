package com.softup.store.utils;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.softup.store.entity.User;
import com.softup.store.interfaces.UserService;

public class StoreUtils {

	@Autowired
	static UserService userService;

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

	public static User findUser() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userService.findByUsername(username);
		return user;
	}

	public static boolean fileUpload(String partname, String path, CommonsMultipartFile file) {

		String ext = FilenameUtils.getExtension(file.getOriginalFilename());
		File dest = new File(path + File.separator + partname + "." + ext);
		
		try {
			file.transferTo(dest);
			return true;
		} catch (IllegalStateException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
}
