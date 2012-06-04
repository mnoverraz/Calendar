package calendar.core.session;

import java.util.HashMap;
import java.util.List;

import javax.ejb.Remote;

import calendar.core.entity.Event;

@Remote
public interface EventHandlerRemote {
	public abstract void create(Event event) throws PersistException;
	public abstract List<Event> read(HashMap<String, Object> params) throws PersistException;
	public abstract void update(Event event) throws PersistException;
	public abstract void delete(Event event) throws PersistException;
}
