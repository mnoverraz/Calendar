package calendar.web.renderer;

import java.util.ArrayList;
import java.util.HashMap;

import calendar.core.application.Config;
import calendar.core.application.utils.DateHelper;
import calendar.core.entity.EventDate;
import calendar.core.exception.CoreException;
import calendar.core.exception.TimeSlotException;

public class ExceptionRenderer {
	
	private static Message ExceptionRenderer(Exception ex) {
		Message message = new Message();
		HashMap<String, Object> content = new HashMap<String, Object>();		
		content.put("error", ex.getMessage());
		message.addElementToBody(content);
		return message;
	}
	
	
	public static Message CoreExceptionRenderer(CoreException ex) {
		Message message = new Message();
		HashMap<String, Object> content = new HashMap<String, Object>();
		content.put("error", ex.detailInformation);
		message.addElementToBody(content);
		return message;
	}
	
	public static Message TimeSlotExceptionRenderer (TimeSlotException ex) {
		Message message = new Message();
		HashMap<String, Object> content = new HashMap<String, Object>();
		@SuppressWarnings("unchecked")
		ArrayList<EventDate> eventDates = (ArrayList<EventDate>) ex.detailInformation;
		ArrayList<HashMap<String, Object>> errors = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> eventMap = null;
		message.state = false;
		for (EventDate eventDate : eventDates) {
			eventMap = new HashMap<String, Object>();
			eventMap.put("start", DateHelper.DateToString(eventDate.getStart(), Config.DATE_FORMAT_LONG));
			eventMap.put("end", DateHelper.DateToString(eventDate.getEnd(), Config.DATE_FORMAT_LONG));
			errors.add(eventMap);
			
		}
		content.put("TimeSlotException", errors);
		message.addElementToBody(content);
		return message;
	}
}
