package calendar.core.ejb.entity;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Rept")
public class RepeatingEvent extends Event {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public RepeatingEvent() {
		super();
	}

	public RepeatingEvent(long id, String title,
			String description, String mode, Date repeatingEnd) {
		super(id, title, description, mode, repeatingEnd);
	}
	
	public RepeatingEvent(long id, String title) {
		super(id, title);
	}
}
