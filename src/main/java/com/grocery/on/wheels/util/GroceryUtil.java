package com.grocery.on.wheels.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GroceryUtil {
	private static final DateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
	
	public static String getFormatDate(Date date) {
		return df.format(date);
	}
}
