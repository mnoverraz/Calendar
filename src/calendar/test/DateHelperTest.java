package calendar.test;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import calendar.core.application.Config;
import calendar.core.application.utils.DateHelper;

public class DateHelperTest {
	private Date start = null;
	private Date end = null;
	
	/**
     * Sets up the test fixture. 
     * (Called before every test case method.)
	 * @throws ParseException 
     */
    @Before
    public void setUp() throws ParseException {
    	start = DateHelper.StringToDate("2011-05-05 22:50", Config.DATE_FORMAT_LONG);
		end = DateHelper.StringToDate("2012-05-05 22:50", Config.DATE_FORMAT_LONG);
    }

	@Test
	public void test_calulateDiff() {
		System.out.println(DateHelper.getTimeBetween(start, end, "y"));	
	}

	@Test
	public void test_repeatDate() {
				
		ArrayList<Date> dates = DateHelper.calculateRecurrentDates(start, end, "m");
		
		for (Date d : dates) {
			System.out.println(d);
		}
		
	}
}
