package calendar.web.utils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;


import calendar.core.application.Config;
import calendar.core.application.utils.DateHelper;
import calendar.core.entity.Event;
import calendar.core.entity.EventDate;
import calendar.core.exception.SystemException;
import calendar.web.exception.FormNotValidException;

public class FormUtils {

	public static Event createEventFromForm(String fDate, String fStartH,
			String fStartM, String fEndH, String fEndM, String fAllDay,
			String fRepeatMode, String fRepeatEnd, String fTitle,
			String fDescription) throws FormNotValidException, SystemException
	{
		Event event = null;
		Date start = null;
		Date end = null;
		boolean allDay = false;
		String repeatMode = null;
		Date repeatEnd = null;
		String title = null;
		String description = null;
		ArrayList<Date> dates = new ArrayList<Date>();
		ArrayList<EventDate> eventDates = new ArrayList<EventDate>();

		HashMap<String, Object> validation = new HashMap<String, Object>();
		validation.put("date", true);
		validation.put("startH",  true);
		validation.put("startM",  true);
		validation.put("endH", true);
		validation.put("endM", true);
		validation.put("allDay", true);
		validation.put("repeatMode", true);
		validation.put("repeatEnd", true);
		validation.put("title", true);
		validation.put("description", false);

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
			if ("true".equals(fAllDay))
				allDay = true;
			else if ("false".equals(fAllDay))
				allDay = false;
			else
				validation.put("allDay", false);
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

		if (fTitle != null && !"".equals(fTitle))
			title = StringUtils.replaceEach(fTitle, new String[]{"&", "\"", "<", ">"}, new String[]{"&amp;", "&quot;", "&lt;", "&gt;"});
		else 
			validation.put("title", false);
		if (fDescription != null && !"".equals(fDescription))
			description = StringUtils.replaceEach(fDescription, new String[]{"&", "\"", "<", ">"}, new String[]{"&amp;", "&quot;", "&lt;", "&gt;"});

		if (repeatMode != null && "n".equals(repeatMode)) {
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

		if (validation.size() > 0) {
			FormNotValidException fe = new FormNotValidException();
			fe.detailInformation = validation;
			throw fe;
		}
		try {
			dates = DateHelper.calculateRecurrentDates(start, repeatEnd,
					repeatMode);
			for (Date d : dates) {
				String dateString = DateHelper.DateToString(d);
				Date eStart = null;
				Date eEnd = null;

				eStart = DateHelper.StringToDate(dateString + " " + fStartH
						+ ":" + fStartM, Config.DATE_FORMAT_LONG);
				eEnd = DateHelper.StringToDate(dateString + " " + fEndH + ":"
						+ fEndM, Config.DATE_FORMAT_LONG);

				EventDate eventDate = new EventDate(eStart, eEnd);
				eventDate.setEvent(event);
				eventDates.add(eventDate);
			}
			event = new Event(0, eventDates, title, description, repeatMode);
		} catch (Exception ex) {
			SystemException se = new SystemException();
			se.detailInformation = "Form not valid unknow exception";
		}

		return event;
	}
}
