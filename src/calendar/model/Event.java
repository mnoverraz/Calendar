package calendar.model;

import java.util.Date;

import calendar.application.Config;
import calendar.application.utils.DateHelper;

public class Event extends Element {
	private Date start;
	private Date end;
	private String title;
	private String description;
	public Event(long id, Date start, Date end, String title, String description) {
		super(id);
		initialize(start, end, title);
		this.description = description;
	}
	
	public Event(long id, Date start, Date end, String title) {
		super(id);
		initialize(start, end, title);
	}
	
	private void initialize(Date start, Date end, String title) {
		this.start = start;
		this.end = end;
		this.title = title;
	}
	public Date getStart() {
		return start;
	}
	public Date getEnd() {
		return end;
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
		ret += "begin:\t\t" + DateHelper.DateToString(start, Config.DATE_FORMAT_LONG) + "\n"; 
		ret += "end:\t\t" + DateHelper.DateToString(end, Config.DATE_FORMAT_LONG) + "\n";  
		
		return  ret;
	}
}
