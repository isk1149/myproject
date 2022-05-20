package com.myproject.webapp.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormat {
	
	public static String yearMonthFormatByKorean(String date) {
		return date.substring(0, 4) + "년 " + date.substring(4, 6) + "월";
	}
	
	public static String yearMonthDayFormatByKorean(String date) {
		return date.substring(0, 4) + "년 " + date.substring(4, 6) + "월 " + date.substring(6, 8) + "일";
	}
	
	public static String yearMonthDayHourMinuteFormatByKorean(String date) {
		return date.substring(0, 4) + "년 " + date.substring(4, 6) + "월 " + date.substring(6, 8) + "일 " + 
				date.substring(8, 10) + "시 " + date.substring(10, 12) + "분 ";
	}
	
	public static String yearMonthDayHourMinuteSecondFormatByKorean(String date) {
		return date.substring(0, 4) + "년 " + date.substring(4, 6) + "월 " + date.substring(6, 8) + "일 " + 
				date.substring(8, 10) + "시 " + date.substring(10, 12) + "분 " + date.substring(12, 14) + "초";
	}
	
	// Date 매개변수
	public static String yearMonthDayHourMinuteFormatByKorean(Date txDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String date = sdf.format(txDate);
		return date.substring(0, 4) + "년 " + date.substring(4, 6) + "월 " + date.substring(6, 8) + "일 " + 
				date.substring(8, 10) + "시 " + date.substring(10, 12) + "분 ";
	}
	
	public static String dateFormatYearToSecond(Date txDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String date = sdf.format(txDate);
		return date.substring(0, 4) + "년 " + date.substring(4, 6) + "월 " + date.substring(6, 8) + "일 " + 
				date.substring(8, 10) + ":" + date.substring(10, 12) + ":" + date.substring(12, 14);
	}
}