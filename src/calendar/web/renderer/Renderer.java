package calendar.web.renderer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import calendar.core.application.Config;
import calendar.core.application.utils.DateHelper;
import calendar.core.model.Event;
import calendar.core.model.EventDate;

public class Renderer {
	public Renderer() {
		
	}
	
	public static String toJSON(ArrayList<HashMap<String, Object>> content) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("[");
		
		
		for (int i = 0; i < content.size(); i++) {
			HashMap<String, Object> inner = content.get(i);
			Iterator<?> it = inner.entrySet().iterator();
			sb.append("{");
			while (it.hasNext()) {
				Object key = it.next();
				Object value = inner.get(key);
				
				sb.append("\"" + key + "\":" + value);
			}
			sb.append("}");
			
			if (inner.size() - (i+1) > 0) {
				sb.append(",");
			}
		}
		sb.append("]");
		
		return sb.toString();
	}
	
	public static String EventRerderer(ArrayList<Event> events) {	
		StringBuilder sb = new StringBuilder();
		
		sb.append("[");
		
		for (int i = 0; i < events.size(); i++) {
			Event event = events.get(i);
			ArrayList<EventDate> eventDates = event.getEventDates();
			for(int j = 0; j < eventDates.size(); j++) {
				EventDate eventDate = eventDates.get(j);
				sb.append("{");
				sb.append("\"id\": \"" + event.getId() + "\",");
				sb.append("\"title\": \"" + event.getTitle() + "\",");
				sb.append("\"start\": \"" + DateHelper.DateToString(eventDate.getStart(), Config.DATE_FORMAT_LONG) + "\",");
				sb.append("\"end\": \"" + DateHelper.DateToString(eventDate.getEnd(), Config.DATE_FORMAT_LONG) + "\",");
				sb.append("\"allDay\": " + eventDate.isAllDay());
				sb.append("}");
				if (eventDates.size() - (j+1) > 0) {
					sb.append(",");
				}
			}
			if (events.size() - (i+1) > 0) {
				sb.append(",");
			}
		}
		sb.append("]");
		
		return sb.toString();
	}
}
