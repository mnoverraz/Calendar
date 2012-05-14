package calendar.web.renderer;

import java.util.ArrayList;

import calendar.core.application.Config;
import calendar.core.application.utils.DateHelper;
import calendar.core.model.Event;
import calendar.core.model.EventDate;

public class FullCalendarRenderer {
	public FullCalendarRenderer() {
		
	}
	
	public String EventRerderer(ArrayList<Event> events) {	
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
				sb.append("\"allDay\": false");
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
