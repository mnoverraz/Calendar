package calendar.core.ejb.session;

import java.util.HashMap;
import java.util.List;

import calendar.core.ejb.entity.Event;

public interface EventHandler {
	public abstract void create(Event event) throws PersistException;
	public abstract List<Event> read(HashMap<String, Object> params) throws PersistException;
	public abstract void update(Event event) throws PersistException;
	public abstract void delete(Event event) throws PersistException;
}
