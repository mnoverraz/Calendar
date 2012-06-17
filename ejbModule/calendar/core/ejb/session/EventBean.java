package calendar.core.ejb.session;

import java.util.HashMap;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import calendar.core.ejb.entity.Event;

/**
 * Handles all CRUD access to events
 * 
 * @author AFFOLTER Nicolas, MEIER Stefan, NOVERRAZ Mathieu
 * @version 2011.06.06
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
		
		if (params == null)
			params = new HashMap<String, Object>();


		query.append(" FROM Event as event");
		query.append(" WHERE");
		query.append(" 1=1");
		if (params.containsKey("id")) 
			query.append(" AND event.id = :id");		
		try {
			Query q = em.createQuery(query.toString());
			if (params.containsKey("id"))
				q.setParameter("id", params.get("id"));			
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
		em.remove(origEvent);
		em.merge(event);
	}

	@Override
	public void delete(Event event) throws PersistException {
		Event origEvent = em.find(Event.class, event.getId());
		em.remove(origEvent);
	}

}
