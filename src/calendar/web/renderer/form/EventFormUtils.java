package calendar.web.renderer.form;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;


import calendar.core.ejb.entity.Event;
import calendar.core.ejb.entity.EventDate;
import calendar.core.ejb.entity.NormalEvent;
import calendar.core.ejb.entity.RepeatingEvent;
import calendar.management.exception.SystemException;
import calendar.management.init.Config;
import calendar.tools.utils.DateHelper;
import calendar.web.exception.FormNotValidException;
/**
 * Returns an event corresponding to a HTML form
 * 
 * @author AFFOLTER Nicolas, MEIER Stefan, NOVERRAZ Mathieu
 * @version 2011.06.06
 */
public class EventFormUtils {

	/**
	 * Checks the validity of the form and if valid returns an event corresponding
	 * to the form input
	 * 
	 * @param fId
	 * @param fDate
	 * @param fStartH
	 * @param fStartM
	 * @param fEndH
	 * @param fEndM
	 * @param fAllDay
	 * @param fRepeatMode
	 * @param fRepeatEnd
	 * @param fTitle
	 * @param fDescription
	 * @return Event
	 * @throws FormNotValidException
	 * @throws SystemException
	 */
	public static Event createEventFromForm(String fId, String fDate, String fStartH,
			String fStartM, String fEndH, String fEndM, String fAllDay,
			String fRepeatMode, String fRepeatEnd, String fTitle,
			String fDescription) throws FormNotValidException, SystemException
	{
		Event event = null;
		long id = 0;
		Date start = null;
		Date end = null;
		boolean allDay = false;
		String repeatMode = null;
		Date repeatEnd = null;
		String title = null;
		String description = "";
		ArrayList<Date> dates = new ArrayList<Date>();

		HashMap<String, Boolean> validation = new HashMap<String, Boolean>();
		validation.put("date", true);
		validation.put("startH",  true);
		validation.put("startM",  true);
		validation.put("endH", true);
		validation.put("endM", true);
		validation.put("repeatMode", true);
		validation.put("repeatEnd", true);
		validation.put("title", true);
		validation.put("description", true);
		validation.put("allDay", true);

		try {
			start = DateHelper.StringToDate(fDate + " " + fStartH + ":"
					+ fStartM, Config.DATE_FORMAT_LONG);
		} catch (ParseException e) {
			validation.put("date", false);
			validation.put("startH",  false);
			validation.put("startM",  false);
		}
		try {
			end = DateHelper.StringToDate(fDate + " " + fEndH + ":" + fEndM,
					Config.DATE_FORMAT_LONG);
		} catch (ParseException e) {
			validation.put("date", false);
			validation.put("endH", false);
			validation.put("endM", false);
		}

		if (start != null && end != null) {
			if (start.after(end)) {
				validation.put("startH",  false);
				validation.put("startM",  false);
				validation.put("endH", false);
				validation.put("endM", false);
			} else if (start == end)
				allDay = true;
		}

		if (!allDay) {
			if ("true".equals(fAllDay) || "on".equals(fAllDay))
				allDay = true;
		}

		if ("n".equals(fRepeatMode))
			repeatMode = "n";
		else if ("d".equals(fRepeatMode))
			repeatMode = "d";
		else if ("w".equals(fRepeatMode))
			repeatMode = "w";
		else if ("2w".equals(fRepeatMode))
			repeatMode = "2w";
		else if ("m".equals(fRepeatMode))
			repeatMode = "m";
		else if ("y".equals(fRepeatMode))
			repeatMode = "y";
		else
			validation.put("repeatMode", false);
		
		if (fId != null && !"".equals(fId))
			id = Integer.parseInt(fId);

		if (fTitle != null && !"".equals(fTitle))
			title = StringUtils.replaceEach(fTitle, new String[]{"&", "\"", "<", ">", "+"}, new String[]{"&amp;", "&quot;", "&lt;", "&gt;", " "});
		else 
			validation.put("title", false);
		if (fDescription != null && !"".equals(fDescription))
			description = StringUtils.replaceEach(fDescription, new String[]{"&", "\"", "<", ">", "+"}, new String[]{"&amp;", "&quot;", "&lt;", "&gt;", " "});

		if (repeatMode != null && !"n".equals(repeatMode)) {
			try {
				repeatEnd = DateHelper.StringToDate(fRepeatEnd);
				if (!repeatEnd.after(start)) {
					validation.put("date", false);
					validation.put("repeatEnd", false);
				}
			} catch (ParseException e) {
				validation.put("repeatEnd", false);
			}
		}

		if (validation.containsValue(false)) {
			FormNotValidException fe = new FormNotValidException();
			fe.detailInformation = validation;
			throw fe;
		}
		try {
			if (repeatEnd != null) {
				event = new RepeatingEvent(id, title, description, repeatMode, repeatEnd);
				dates = DateHelper.calculateRecurrentDates(start, repeatEnd, repeatMode);
				
				for (Date d : dates) {
					String dateString = DateHelper.DateToString(d);
					Date eStart = null;
					Date eEnd = null;
	
					eStart = DateHelper.StringToDate(dateString + " " + fStartH
							+ ":" + fStartM, Config.DATE_FORMAT_LONG);
					eEnd = DateHelper.StringToDate(dateString + " " + fEndH + ":"
							+ fEndM, Config.DATE_FORMAT_LONG);
					event.addEventDate(new EventDate(eStart, eEnd));
				}
			}
			else {
				event = new NormalEvent(id, title, description);
			}
			event.addEventDate(new EventDate(start, end));
			
		} catch (Exception ex) {
			SystemException se = new SystemException();
			se.detailInformation = "Form not valid unknow exception";
		}

		return event;
	}
}