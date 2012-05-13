package calendar.core.application.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import calendar.core.application.Config;


/**
 * This class is a helper for date transformation and for all kind 
 * of date manipulations
 * 
 * 
 * @author BELLATALLA Alain, MEIER Stefan, NOVERRAZ Mathieu
 * @version 2011.0704
 */
public class DateHelper {

	/**
	 * Transforms a date String to a date
	 * @param date
	 * @throws ParseException
	 */
	public static Date StringToDate(String date) throws ParseException {
		return StringToDate(date, Config.DATE_FORMAT_SHORT);
	}
	
	/**
	 * Transforms a date String to a date specified by dateFormat
	 * @param date
	 * @param dateFormat
	 * @throws ParseException
	 */
	public static Date StringToDate(String date, String dateFormat) throws ParseException {
		DateFormat sdf = new SimpleDateFormat(dateFormat);
		return sdf.parse(date);
	}
	
	/**
	 * Transforms a date to a formatted string
	 * @param date
	 */
	public static String DateToString(Date date) {
		return DateToString(date, Config.DATE_FORMAT_SHORT);
	}
	
	/**
	 * Transforms a date to a formatted string as specified in dateFormat
	 * @param date
	 * @param dateFormat
	 */
	public static String DateToString(Date date, String dateFormat) {
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		return sdf.format(date);
	}
	
	/**
	 * Gets the hour of a date
	 * @param date
	 */
	public static int getHour(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("H");
		return Integer.parseInt(sdf.format(date));
	}
	
	/**
	 * Gets the minutes of a date
	 * @param date
	 * @return
	 */
	public static int getMinutes(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("mm");
		return Integer.parseInt(sdf.format(date));
	}
		
	/**
	 * Calculates the differences between two dates
	 * @param date1
	 * @param date2
	 */
	public static int DateDiff(Date date1, Date date2){
		return (int) (date2.getTime()-date1.getTime())/60000;
		
	}
	
	/**
	 * Gets todays date
	 * @return
	 */
	public static  Date getToday() {
		return  new Date();  
	}
	
	/**
	 * Gets the next days date for specified date
	 * @param day
	 */
	public static Date getNextDay(Date day) {
		//---Get Calendar object set to the date and time of the given Date object 
		GregorianCalendar cal = (GregorianCalendar) GregorianCalendar.getInstance();   
		cal.setTime(day);

		//---Next day = day + 1
		cal.add(GregorianCalendar.DATE, 1);

		//---Put it back in the Date object   
		return cal.getTime();  
	}
	
	/**
	 * Gets the previous days date for specified date
	 * @param day
	 * @return
	 */
	public static Date getPreviousDay(Date day) {
		//---Get Calendar object set to the date and time of the given Date object 
		GregorianCalendar cal = (GregorianCalendar) GregorianCalendar.getInstance();   
		cal.setTime(day);
		
		//---Previous day = day - 1
		cal.add(GregorianCalendar.DATE, -1);

		//---Put it back in the Date object   
		return cal.getTime();  
	}
	
	/**
	 * Gets the day number for current week (1 = Sunday)
	 * @param date
	 */
	public static int DayOfWeek(Date date) {
		GregorianCalendar cal = (GregorianCalendar) GregorianCalendar.getInstance();   
		cal.setTime(date);
		
		return cal.get(Calendar.DAY_OF_WEEK);
	}
	

}
