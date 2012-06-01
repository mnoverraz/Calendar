package calendar.web.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import calendar.core.application.Config;
import calendar.core.application.utils.DateHelper;
import calendar.core.controller.EventController;
import calendar.core.entity.Event;
import calendar.core.entity.EventDate;
import calendar.core.exception.CoreException;
import calendar.core.exception.TimeSlotException;
import calendar.web.renderer.Message;
import calendar.web.renderer.TimeSlotExceptionRenderer;


public class WebEventController extends WebController<EventController> {
	

	public WebEventController(EventController controller) {
		super(controller);
	}

	@Override
	public Message create(HashMap<String, String> params) {
		Event event = null;
		Message message = new Message();
		
		try {
			Date start = null;
			Date end = null;
			try {
				start = DateHelper.StringToDate("2012-06-01 21:50", Config.DATE_FORMAT_LONG);
				end = DateHelper.StringToDate("2012-06-20 23:50", Config.DATE_FORMAT_LONG);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ArrayList<Date> dates = DateHelper.calculateRecurrentDates(start, end, "d");
			
			ArrayList<EventDate> eventDates = new ArrayList<EventDate>();
			
			eventDates.add(new EventDate(start, end));
			for(Date d : dates) {
				String dateString = DateHelper.DateToString(d, Config.DATE_FORMAT_SHORT);
				Date eStart = null;
				Date eEnd = null;
				try {
					eStart = DateHelper.StringToDate(dateString + " " + DateHelper.DateToString(start, Config.TIME_FORMAT));
					eEnd = DateHelper.StringToDate(dateString + " " + DateHelper.DateToString(end, Config.TIME_FORMAT));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				eventDates.add(new EventDate(eEnd, eStart));
			}
			event = new Event(4, eventDates, "event 3", "description 3", "m");
			controller.create(event);
			HashMap<String, Object> eventMap = null;
			for (EventDate eventDate : event.getEventDates()) {
				eventMap = new HashMap<String, Object>();
				eventMap.put("id", event.getId());
				eventMap.put("title", event.getTitle());
				eventMap.put("start", DateHelper.DateToString(eventDate.getStart(), Config.DATE_FORMAT_LONG));
				eventMap.put("end", DateHelper.DateToString(eventDate.getEnd(), Config.DATE_FORMAT_LONG));
				eventMap.put("allDay", eventDate.isAllDay());
				
				message.addElementToBody(eventMap);
			}
			
		} catch (TimeSlotException e) {
			message = TimeSlotExceptionRenderer.render(e);
		}
		catch (CoreException e) {
			
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
		} 
		catch (TimeSlotException e) {
			Object detailInformation = e.detailInformation;
			@SuppressWarnings("unchecked")
			ArrayList<EventDate> eventDates = (ArrayList<EventDate>)detailInformation;
			message.state = false;

		}
		catch (CoreException e) {
			
		}
				
		for (Event event : events) {
			HashMap<String, Object> eventMap = null;
			for (EventDate eventDate : event.getEventDates()) {
				eventMap = new HashMap<String, Object>();
				eventMap.put("id", event.getId());
				eventMap.put("title", event.getTitle());
				eventMap.put("start", DateHelper.DateToString(eventDate.getStart(), Config.DATE_FORMAT_LONG));
				eventMap.put("end", DateHelper.DateToString(eventDate.getEnd(), Config.DATE_FORMAT_LONG));
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
