package calendar.core.test;

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
    	start = DateHelper.StringToDate("2011-05-05 21:50", Config.DATE_FORMAT_LONG);
		end = DateHelper.StringToDate("2012-05-05 22:50", Config.DATE_FORMAT_LONG);
    }

	@Test
	public void test_calulateDiff() {
		int y = DateHelper.getIntervalSizeBetween(start, end, "y");	
		int m = DateHelper.getIntervalSizeBetween(start, end, "m");	
		int d = DateHelper.getIntervalSizeBetween(start, end, "d");	
		int twoW = DateHelper.getIntervalSizeBetween(start, end, "2w");	
		int w = DateHelper.getIntervalSizeBetween(start, end, "w");	
		assertEquals(366, d);
		assertEquals(52, w);
		assertEquals(26, twoW);
		assertEquals(0, y);
		assertEquals(12, m);
	}

	@Test
	public void test_repeatDate() {
				
		ArrayList<Date> dates = DateHelper.calculateRecurrentDates(start, end, "2w");
		
		for (Date d : dates) {
			System.out.println(d);
		}
		
	}
}
