package calendar.core.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Impt")
public class ImportantEvent extends Event {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ImportantEvent() {
		super();
	}

	public ImportantEvent(int id, String title,
			String description, String mode) {
		super(id, title, description, mode);
	}
	
	public ImportantEvent(int id, String title) {
		super(id, title);
	}
}
