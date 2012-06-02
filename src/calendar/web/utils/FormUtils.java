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

		HashMap<String, Object> errors = new HashMap<String, Object>();

		try {
			start = DateHelper.StringToDate(fDate + " " + fStartH + ":"
					+ fStartM, Config.DATE_FORMAT_LONG);
		} catch (ParseException e) {
			errors.put("date", "'" + fDate + "'");
			errors.put("startH",  "'" + fStartH +  "'");
			errors.put("startM",  "'" + fStartM +  "'");
		}
		try {
			end = DateHelper.StringToDate(fDate + " " + fEndH + ":" + fEndM,
					Config.DATE_FORMAT_LONG);
		} catch (ParseException e) {
			errors.put("date", "'" + fDate + "'");
			errors.put("endH", "'" + fEndH + "'");
			errors.put("endM", "'" + fEndM + "'");
		}

		if (start != null && end != null) {
			if (start.after(end)) {
				errors.put("startH",  "'" + fStartH +  "'");
				errors.put("startM",  "'" + fStartM +  "'");
				errors.put("endH", "'" + fEndH + "'");
				errors.put("endM", "'" + fEndM + "'");
			} else if (start == end)
				allDay = true;
		}

		if (!allDay) {
			if ("true".equals(fAllDay))
				allDay = true;
			else if ("false".equals(fAllDay))
				allDay = false;
			else
				errors.put("allDay", "'" + fAllDay + "'");
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
			errors.put("repeatMode", "'" + fRepeatMode + "'");

		if (fTitle != null && !"".equals(fTitle))
			title = StringUtils.replaceEach(fTitle, new String[]{"&", "\"", "<", ">"}, new String[]{"&amp;", "&quot;", "&lt;", "&gt;"});
		else 
			errors.put("title", "'" + fTitle + "'");
		if (fDescription != null && !"".equals(fDescription))
			description = StringUtils.replaceEach(fDescription, new String[]{"&", "\"", "<", ">"}, new String[]{"&amp;", "&quot;", "&lt;", "&gt;"});

		if (repeatMode != null && "n".equals(repeatMode)) {
			try {
				repeatEnd = DateHelper.StringToDate(fRepeatEnd);
				if (!repeatEnd.after(start)) {
					errors.put("date", fDate);
					errors.put("repeatEnd", fRepeatEnd);
				}
			} catch (ParseException e) {
				errors.put("repeatEnd", "'" + fRepeatEnd + "'");
			}
		}

		if (errors.size() > 0) {
			FormNotValidException fe = new FormNotValidException();
			fe.detailInformation = errors;
			System.out.println(errors);
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

				eventDates.add(new EventDate(eStart, eEnd));
			}
			event = new Event(0, eventDates, title, description, repeatMode);
		} catch (Exception ex) {
			SystemException se = new SystemException();
			se.detailInformation = "Form not valid unknow exception";
		}

		return event;
	}
}
