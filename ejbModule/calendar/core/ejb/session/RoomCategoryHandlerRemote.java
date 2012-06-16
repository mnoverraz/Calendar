package calendar.core.ejb.session;

import java.util.HashMap;
import java.util.List;

import javax.ejb.Remote;

import calendar.core.ejb.entity.RoomCategory;

@Remote
public interface RoomCategoryHandlerRemote {
	public abstract void create(RoomCategory roomCategory) throws PersistException;
	public abstract List<RoomCategory> read(HashMap<String, Object> params) throws PersistException;
	public abstract void update(RoomCategory roomCategory) throws PersistException;
	public abstract void delete(RoomCategory roomCategory) throws PersistException;
}
