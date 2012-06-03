package calendar.core.session;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;


import calendar.core.entity.Event;

/**
 * Session Bean implementation class EventHandler
 */
@Stateless
public class EventHandler implements EventHandlerLocal {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Event> get(Date fSince, Date fUntil, long fId)
			throws PersistanceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Event> get() throws PersistanceException {
		List<Event> events = null;
		try {
		      events = em.createQuery("SELECT e FROM Event e").getResultList();
		    } catch (PersistenceException ex) {
		      ex.printStackTrace();
		      throw new PersistanceException();
		    }
		return events;
	}

}
