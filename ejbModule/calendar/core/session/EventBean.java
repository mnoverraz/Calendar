package calendar.core.session;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

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
		/*
		if (params.containsKey("start"))
			query.append(" AND date.start >= :start");
		if (params.containsKey("end"))
			query.append(" AND date.end <= :end");*/
		
		else {
			/*if (params.containsKey("start") || params.containsKey("end")) {
				query.append(" LEFT OUTER JOIN event.eventDates as date");
				query.append(" WHERE event.id = date.event.id");
			}*/

		}
		
		try {
			Query q = em.createQuery(query.toString());
			if (params.containsKey("id"))
				q.setParameter("id", params.get("id"));
			/*if (params.containsKey("start"))
				q.setParameter("start", params.get("start"));
			if (params.containsKey("end"))
				q.setParameter("end", params.get("end"));*/
			
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
