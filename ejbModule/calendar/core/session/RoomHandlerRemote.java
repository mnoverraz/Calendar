package calendar.core.session;

import java.util.HashMap;
import java.util.List;

import javax.ejb.Remote;

import calendar.core.entity.Room;

@Remote
public interface RoomHandlerRemote {
	public abstract void create(Room room) throws PersistException;
	public abstract List<Room> read(HashMap<String, Object> params) throws PersistException;
	public abstract void update(Room room) throws PersistException;
	public abstract void delete(Room room) throws PersistException;
}
