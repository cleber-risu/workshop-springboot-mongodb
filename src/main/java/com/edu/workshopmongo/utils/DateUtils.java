package com.edu.workshopmongo.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class DateUtils {
	
	/**
	 * @param year
	 * @param month
	 * @param day
	 * @param hour
	 * @param minute
	 * @return
	 */
	public static Instant createInstantGMT(int year, int month, int day, int hour, int minute) {
	    LocalDateTime ldt = LocalDateTime.of(year, month, day, hour, minute);
	    return ldt.atZone(ZoneId.of("GMT")).toInstant();
	}
}
