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
		StringBuffer query = new StringBuffer();
		//query.append("SELECT e FROM Event e");

		query.append("SELECT");
		query.append("E.event_id,");
		query.append("E.title,");
		query.append("E.description,");
		query.append("D.start,");
		query.append("D.end,");
		query.append("D.event_date_id,");
		query.append("E.mode");
		query.append("FROM events E, event_dates D");
		query.append("WHERE E.event_id = D.event_event_id");
		/*query.append("FROM events E");
		query.append("   LEFT OUTER JOIN event_dates D");
		query.append("        ON E.event_id = D.event_event_id ");*/
		try {
			events = em.createQuery(query.toString()).getResultList();
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
