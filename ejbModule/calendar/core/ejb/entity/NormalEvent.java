package calendar.core.ejb.entity;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Norm")
public class NormalEvent extends Event implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public NormalEvent() {
		super();
	}

	public NormalEvent(int id, String title,
			String description, String mode) {
		super(id, title, description, mode);
	}
	
	public NormalEvent(int id, String title) {
		super(id, title);
	}
}
