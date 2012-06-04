package calendar.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity (name="EventDate")
@Table (name="event_dates")
public class EventDate implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column (name="event_date_id")
	private int id;
	
	@ManyToOne
	@JoinColumn
	private Event event;
	
	@Column
	@Temporal(TemporalType.DATE)
	private Date start;
	@Column
	@Temporal(TemporalType.DATE)
	private Date end;
	@Column
	private boolean allDay = false;
	
	public EventDate() {
		
	}
	
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Event getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}
}
