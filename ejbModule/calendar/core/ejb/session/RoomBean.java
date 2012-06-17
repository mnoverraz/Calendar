package calendar.core.ejb.session;

import java.util.HashMap;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import calendar.core.ejb.entity.Room;

/**
 * Handles CRUD access on Rooms
 * 
 * @author AFFOLTER Nicolas, MEIER Stefan, NOVERRAZ Mathieu
 * @version 2011.06.06
 */
@Stateless
public class RoomBean implements RoomHandlerRemote, RoomHandlerLocal {

	@PersistenceContext
	private EntityManager em;

	@Override
	public void create(Room room) throws PersistException {
		em.persist(room);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Room> read(HashMap<String, Object> params)
			throws PersistException {
		List<Room> rooms = null;
		StringBuffer query = new StringBuffer();

		if (params == null)
			params = new HashMap<String, Object>();

		query.append("SELECT r");
		query.append(" FROM Room as r ");

		if (params.containsKey("id"))
			query.append(" WHERE r.id = :id");

		try {
			Query q = em.createQuery(query.toString());

			if (params.containsKey("id"))
				q.setParameter("id", params.get("id"));
			rooms = q.getResultList();
		} catch (PersistenceException ex) {
			ex.printStackTrace();
			throw new PersistException();
		}

		return rooms;

	}

	@Override
	public void update(Room room) throws PersistException {
		Room origRoom = em.find(Room.class, room.getId());
		origRoom.setLocal(room.getLocal());
		origRoom.setName(room.getName());
		origRoom.setDescription(room.getDescription());
		origRoom.setRoomCategory(room.getRoomCategory());
	}

	@Override
	public void delete(Room room) throws PersistException {
		Room origRoom = em.find(Room.class, room.getId());
		em.remove(origRoom);
	}

}