package calendar.core.session;

import java.util.HashMap;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import calendar.core.entity.Event;

/**
 * Session Bean implementation class EventHandler
 */
@Stateless
public class EventBean implements EventHandlerLocal, EventHandlerRemote {

	@PersistenceContext
	private EntityManager em;

	@Override
	public void create(Event event) throws PersistException {
		em.merge(event);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Event> read(HashMap<String, Object> params)
			throws PersistException {
		List<Event> events = null;
		StringBuffer query = new StringBuffer();

		query.append("FROM Event event ");
		
		try {
			Query q = em.createQuery(query.toString());
			//q.setParameter("id", null);
			
			events = q.getResultList();
		} catch (PersistenceException ex) {
			ex.printStackTrace();
			throw new PersistException();
		}
		return events;
	}

	@Override
	public void update(Event event) throws PersistException {
		Event origEvent = em.find(Event.class, event.getId());
		origEvent.setMode(event.getMode());
		origEvent.setTitle(event.getTitle());
		origEvent.setDescription(event.getDescription());
		origEvent.setEventDates(event.getEventDates());
	}

	@Override
	public void delete(Event event) throws PersistException {
		Event origEvent = em.find(Event.class, event.getId());
		em.remove(origEvent);
	}

}
