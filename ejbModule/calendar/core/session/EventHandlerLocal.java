package calendar.core.session;

import java.util.Date;
import java.util.List;


import javax.ejb.Local;

import calendar.core.entity.Event;

@Local
public interface EventHandlerLocal {
	public List<Event> get(Date fSince, Date fUntil, long fId) throws PersistanceException;
	
	public List<Event> get() throws PersistanceException;
	
	/*public List<Event> get(long id) throws PersistanceException;
	
	public List<Event> get(Date begin) throws PersistanceException;
	
	public List<Event> get(Date begin, Date end) throws PersistanceException;*/
	
}