package calendar.core.model;

import java.util.ArrayList;

import calendar.core.application.Config;
import calendar.core.application.utils.DateHelper;

public class Event extends Element {
	private ArrayList<EventDate> eventDates;
	private String title;
	private String description;
		
	public Event(long id, ArrayList<EventDate> eventDates, String title,
			String description) {
		super(id);
		initialize(eventDates, title);
		this.description = description;
	}
	
	public Event(long id, ArrayList<EventDate> eventDates, String title) {
		super(id);
		initialize(eventDates, title);
	}

	private void initialize(ArrayList<EventDate> eventDates, String title) {
		this.eventDates = eventDates;
		this.title = title;
	}
	
	

	public ArrayList<EventDate> getEventDates() {
		return eventDates;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String toString() {
		String ret = "";
		ret += "id:\t\t" + String.valueOf(super.getId()) + "\n";
		ret += "title:\t\t" + title + "\n";
		ret += "description:\t" + String.valueOf(description) + "\n";
		
		ret += "dates\n";
		ret += "-------\n";
		for (EventDate eventDate : eventDates) {
			ret += "start:\t\t" + DateHelper.DateToString(eventDate.getStart(), Config.DATE_FORMAT_LONG) + "\n"; 
			ret += "end:\t\t" + DateHelper.DateToString(eventDate.getEnd(), Config.DATE_FORMAT_LONG) + "\n";  
		}
		ret += "-------";
		
		return  ret;
	}
}
