package calendar.core.session;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import calendar.core.entity.Event;
import calendar.core.entity.EventDate;

/**
 * Session Bean implementation class EventHandler
 */
@Stateless
public class EventBean implements EventHandlerLocal, EventHandlerRemote {

	@PersistenceContext
	private EntityManager em;

	@Override
	public void create(Event event) throws PersistException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Event> read(HashMap<String, Object> params)
			throws PersistException {
		List<Event> events = null;
		ArrayList<EventDate> eventDates = new ArrayList<EventDate>();

		try {
		      events = em.createQuery("SELECT e FROM Event e").getResultList();
		    } catch (PersistenceException ex) {
		      ex.printStackTrace();
		      throw new PersistException();
		    }
		return events;
	}

	@Override
	public void update(Event event) throws PersistException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Event event) throws PersistException {
		// TODO Auto-generated method stub
		
	}

}
