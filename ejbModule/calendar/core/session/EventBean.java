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

	@Override
	public List<Event> read(HashMap<String, Object> params)
			throws PersistException {
		List<Event> events = null;
		StringBuffer query = new StringBuffer();

		//query.append("SELECT ");
		//query.append("event ");
		query.append("FROM Event event ");
		//query.append("LEFT OUTER JOIN event.eventDates ");
		//query.append("WHERE event.id=:id");
		
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
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Event event) throws PersistException {
		// TODO Auto-generated method stub

	}

}
