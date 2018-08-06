package com.jiang.connectgame.util;

public class UtilFormat {
	
	public static String getTime(int time) {
		int hour = time / 3600;
		int min_sec = time % 3600;
		int min = min_sec / 60;
		int sec = min_sec % 60;
		String str_hour = String.valueOf(hour);
		String str_min = String.valueOf(min);
		String str_sec = String.valueOf(sec);

		if (str_hour.length() < 2) {
			str_hour = "0" + str_hour;
		}
		if (str_min.length() < 2) {
			str_min = "0" + str_min;
		}
		if (str_sec.length() < 2) {
			str_sec = "0" + str_sec;
		}
		
		String str_time = "";
		if (str_hour.equals("00"))
			str_time =  str_min + ":" + str_sec;
		else
			str_time = str_hour + ":" + str_min + ":" + str_sec;
		
		return str_time;
	}
}