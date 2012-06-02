package calendar.web.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import calendar.core.application.Config;
import calendar.core.application.utils.DateHelper;
import calendar.core.controller.EventController;
import calendar.core.entity.Event;
import calendar.core.entity.EventDate;
import calendar.core.exception.CoreException;
import calendar.core.exception.GenericCoreException;
import calendar.core.exception.TimeSlotException;
import calendar.web.renderer.ExceptionRenderer;
import calendar.web.renderer.Message;

public class WebEventController extends WebController<EventController> {

	public WebEventController(EventController controller) {
		super(controller);
	}

	@Override
	public Message create(HashMap<String, String> params) {
		Event event = null;
		Message message = new Message();

		if (params != null) {
			Date date = null;
			int startH = 0;
			int endH = 0;
			int startM = 0;
			int endM = 0;
			boolean allDay = false;
			String repeatMode = "n";
			Date repeatEnd = null;
			String description = null;
			
			Iterator<Entry<String, String>> it = params.entrySet().iterator();
			try {
			while (it.hasNext()) {
				Object key = it.next().getKey();
				Object value = params.get(key);
				

				if ("startH".equals(key))
					startH = Integer.parseInt((String)value);
				if ("endH".equals(key))
					endH = Integer.parseInt((String)value);
				if ("startM".equals(key))
					startM = Integer.parseInt((String)value);
				if ("endM".equals(key))
					endM = Integer.parseInt((String)value);
				if ("date".equals(key)) {
					try {
						date = DateHelper.StringToDate((String)value);
					} catch (ParseException e) {
						GenericCoreException coreException = new GenericCoreException();
						coreException.detailInformation = e.getMessage();
						throw coreException;
					}
				}
				if ("allDay".equals(key) && "true".equals((String)value))
					allDay = true;
				if ("repeatMode".equals(key) && !"n".equals(value))
					repeatMode = (String)value;
				if ("repeatEnd".equals(key) && !"n".equals(repeatMode)) {
					try {
						repeatEnd = DateHelper.StringToDate((String)value);
					} catch (ParseException e) {
						GenericCoreException coreException = new GenericCoreException();
						coreException.detailInformation = e.getMessage();
						throw coreException;
					}
				}
				if ("description".equals(key))
					description = (String)value;
			}
			
				String sDate = "2012-06-01";
				String sRepeatEnd = "2012-06-20";
				
				String sStartHour = "21:50";
				String sEndHour = "23:50";

				Date dDate = null;
				Date rEnd = null;

					dDate = DateHelper.StringToDate(sDate);
					rEnd = DateHelper.StringToDate(sRepeatEnd);

				ArrayList<Date> dates = DateHelper.calculateRecurrentDates(
						dDate, rEnd, "d");

				ArrayList<EventDate> eventDates = new ArrayList<EventDate>();

				//eventDates.add(new EventDate(start, end));
				for (Date d : dates) {
					String dateString = DateHelper.DateToString(d);
					Date eStart = null;
					Date eEnd = null;

						eStart = DateHelper.StringToDate(dateString
								+ " "
								+ sStartHour, Config.DATE_FORMAT_LONG);
						eEnd = DateHelper.StringToDate(dateString
								+ " "
								+ sEndHour, Config.DATE_FORMAT_LONG);


					eventDates.add(new EventDate(eStart, eEnd));
				}
				event = new Event(4, eventDates, "event 3", "description 3",
						"d");
				controller.create(event);
				HashMap<String, Object> eventMap = null;
				for (EventDate eventDate : event.getEventDates()) {
					eventMap = new HashMap<String, Object>();
					eventMap.put("id", event.getId());
					eventMap.put("title", event.getTitle());
					eventMap.put("start", DateHelper.DateToString(
							eventDate.getStart(), Config.DATE_FORMAT_LONG));
					eventMap.put("end", DateHelper.DateToString(
							eventDate.getEnd(), Config.DATE_FORMAT_LONG));
					eventMap.put("allDay", eventDate.isAllDay());

					message.addElementToBody(eventMap);
				}

			} catch (Exception e) {
				ExceptionRenderer exRenderer = new ExceptionRenderer(e);
				message = exRenderer.getMessage();
			}
		}

		return message;
	}

	@Override
	public Message read(HashMap<String, String> params) {
		ArrayList<Event> events = null;
		Message message = new Message();
		message.state = true;

		try {
			events = (ArrayList<Event>) controller.read(null);
		} catch (TimeSlotException e) {
			Object detailInformation = e.detailInformation;
			@SuppressWarnings("unchecked")
			ArrayList<EventDate> eventDates = (ArrayList<EventDate>) detailInformation;
			message.state = false;

		} catch (CoreException e) {

		}

		for (Event event : events) {
			HashMap<String, Object> eventMap = null;
			for (EventDate eventDate : event.getEventDates()) {
				eventMap = new HashMap<String, Object>();
				eventMap.put("id", event.getId());
				eventMap.put("title", event.getTitle());
				eventMap.put("start", DateHelper.DateToString(
						eventDate.getStart(), Config.DATE_FORMAT_LONG));
				eventMap.put("end", DateHelper.DateToString(eventDate.getEnd(),
						Config.DATE_FORMAT_LONG));
				eventMap.put("allDay", eventDate.isAllDay());

				message.addElementToBody(eventMap);
			}
		}

		return message;
	}

	@Override
	public Message update(HashMap<String, String> params) {
		System.out.println("update");
		return null;
	}

	@Override
	public Message delete(HashMap<String, String> params) {
		System.out.println("delete");
		return null;
	}

}
