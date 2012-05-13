package calendar.application;

import java.util.ResourceBundle;

/**
 * Global configuration class, holds all application parameters
 * 
 * @author BELLATALLA Alain, MEIER Stefan, NOVERRAZ Mathieu
 * @version 2011.0704
 */
public class Config {
	
	public static String DEFAULT_LANG = null;
	public static String CURRENT_DATE = null;
	
	public static String DATE_FORMAT_SHORT = "yyyy-MM-dd";
	
	public static String TIME_FORMAT = "HH:mm";
	public static final String DATE_FORMAT_LONG = DATE_FORMAT_SHORT + " " + TIME_FORMAT;
	
	public static int CALENDAR_DAY_START = 8;
	public static int CALENDAR_DAY_END = 19;
	public static int CALENDAR_DAY_INTERVAL = 15;
	

	public static ResourceBundle RESSOURCE_BUNDLE = null;
}
