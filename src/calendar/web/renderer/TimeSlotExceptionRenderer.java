package calendar.web.renderer;

import java.util.ArrayList;
import java.util.HashMap;

import calendar.core.application.Config;
import calendar.core.application.utils.DateHelper;
import calendar.core.entity.EventDate;
import calendar.core.exception.TimeSlotException;

public class TimeSlotExceptionRenderer {
	public static Message render (TimeSlotException ex) {
		Message message = new Message();
		HashMap<String, Object> content = new HashMap<String, Object>();
		@SuppressWarnings("unchecked")
		ArrayList<EventDate> eventDates = (ArrayList<EventDate>) ex.detailInformation;
		HashMap<String, Object> errorMap = new HashMap<String, Object>();
		HashMap<String, Object> eventMap = null;
		message.state = false;
		for (EventDate eventDate : eventDates) {
			eventMap = new HashMap<String, Object>();
			eventMap.put("\"start\"", "\"" + DateHelper.DateToString(eventDate.getStart(), Config.DATE_FORMAT_LONG) +"\"");
			eventMap.put("\"end\"", "\"" + DateHelper.DateToString(eventDate.getEnd(), Config.DATE_FORMAT_LONG) +"\"");
			errorMap.put(String.valueOf(eventDate.getId()), eventMap);
			
		}
		content.put("error", eventMap);
		message.addElementToBody(content);
		return message;
	}
}
