package calendar.core.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity (name="Event")
@Table (name="events")
public class Event implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column (name="event_id")
	private long id;
	
	@OneToMany(mappedBy="event")
	private List<EventDate> eventDates;
	@Column
	private String title;
	@Column
	private String description;
	
	@Column 
	private String mode;	
	
	public Event(long id, List<EventDate> eventDates, String title,
			String description, String mode) {
		initialize(id, eventDates, title);
		this.description = description;
		this.mode = mode;
	}
	
	public Event(long id, List<EventDate> eventDates, String title) {
		
		initialize(id, eventDates, title);
	}

	private void initialize(long id, List<EventDate> eventDates, String title) {
		this.id = id;
		this.setEventDates(eventDates);
		this.title = title;
	}
	
	
	
	public List<EventDate> getEventDates() {
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
		ret += "id:\t\t" + String.valueOf(id) + "\n";
		ret += "title:\t\t" + title + "\n";
		ret += "description:\t" + String.valueOf(description) + "\n";
		
		ret += "dates\n";
		ret += "-------\n";
		for (EventDate eventDate : getEventDates()) {
			ret += "start:\t\t" + eventDate.getEnd() + "\n"; 
			ret += "end:\t\t" + eventDate.getStart();
		}
		ret += "-------";
		
		return  ret;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	private void setEventDates(List<EventDate> eventDates) {
		this.eventDates = eventDates;
	}

	public String getMode() {
		return mode;
	}
}
