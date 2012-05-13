package calendar.web.renderer;

import java.util.ArrayList;

import calendar.core.application.utils.DateHelper;
import calendar.core.model.Event;
import calendar.core.model.EventDate;

public class FullCalendarRenderer {
	public FullCalendarRenderer() {
		
	}
	
	public String EventRerderer(ArrayList<Event> events) {
		
		/*
		 * {
    events: [
        {
            title: 'Event1',
            start: '2011-04-04'
        },
        {
            title: 'Event2',
            start: '2011-05-05'
        }
        // etc...
    ],
    color: 'yellow',   // an option!
    textColor: 'black' // an option!
}
		 */
		
		StringBuilder sb = new StringBuilder();
		sb.append("events : [");
		
		for (Event event : events) {

			for (EventDate eventDate : event.getEventDates()) {
				sb.append("{");
				sb.append("id: '" + event.getId() + "',");
				sb.append("title: '" + event.getTitle() + "',");
				sb.append("start: '" + eventDate.getStart() + "',");
				sb.append("end: '" + eventDate.getEnd() + "',");
				sb.append("},");
			}

		}
		sb.append("]");
		
		return sb.toString();
	}
}
