package calendar.core.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
	private int id;
	
	@OneToMany(mappedBy="event", fetch = FetchType.EAGER, cascade={CascadeType.MERGE})
	private List<EventDate> eventDates;
	@Column
	private String title;
	@Column
	private String description;
	
	@Column 
	private String mode;	
	
	public Event() {
		
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public Event(int id, List<EventDate> eventDates, String title,
			String description, String mode) {
		initialize(id, eventDates, title);
		this.description = description;
		this.mode = mode;
	}
	
	public Event(int id, List<EventDate> eventDates, String title) {
		
		initialize(id, eventDates, title);
	}

	private void initialize(int id, List<EventDate> eventDates, String title) {
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private void setEventDates(List<EventDate> eventDates) {
		this.eventDates = eventDates;
	}

	public String getMode() {
		return mode;
	}
}
