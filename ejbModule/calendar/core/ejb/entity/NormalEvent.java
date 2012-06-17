package calendar.core.ejb.entity;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Norm")
public class NormalEvent extends Event implements Serializable{

	private static final long serialVersionUID = 1L;
	public NormalEvent() {
		super();
	}

	public NormalEvent(long id, String title,
			String description) {
		super(id, title, description, "n");
	}
	
	public NormalEvent(long id, String title) {
		super(id, title);
	}
}
