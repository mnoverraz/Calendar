package calendar.core.model;

import java.util.Date;

public class EventDate {
	private Date start;
	private Date end;
	private boolean allDay = false;;
	
	public EventDate(Date start, Date end) {
		this.start = start;
		this.end = end;
		
		if ((start).equals(end))
			this.allDay = true;
	}
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	public boolean isAllDay() {
		return allDay;
	}
	public void setAllDay(boolean allDay) {
		this.end = this.start;
		this.allDay = allDay;
	}
}
