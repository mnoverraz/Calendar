package calendar.core.session;

import java.util.HashMap;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import calendar.core.entity.RoomCategory;

/**
 * Session Bean implementation class RoomCategoryBean
 */
@Stateless
public class RoomCategoryBean implements RoomCategoryHandlerRemote, RoomCategoryHandlerLocal {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void create(RoomCategory roomCategory) throws PersistException {
		em.merge(roomCategory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RoomCategory> read(HashMap<String, Object> params)
			throws PersistException {

		/* ORIGINAL */
//		List<RoomCategory> roomCategories = null;
//		StringBuffer query = new StringBuffer();
//
//		query.append("FROM RoomCategory roomCategory ");
//		
//		try {
//			Query q = em.createQuery(query.toString());
//			//q.setParameter("id", null);
//			
//			roomCategories = q.getResultList();
//		} catch (PersistenceException ex) {
//			ex.printStackTrace();
//			throw new PersistException();
//		}
//		return roomCategories;
		/* ----------------------------------------------- */
		
		
		List<RoomCategory> roomCategories = null;
		StringBuffer query = new StringBuffer();
		
		query.append("SELECT rc");
		query.append(" FROM RoomCategory as rc ");
		
		if (params != null)
			if (params.containsKey("id"))
				query.append(" WHERE rc.id = :id");
		
		try {
			Query q = em.createQuery(query.toString());
			if (params != null)
			if (params.containsKey("id"))
					q.setParameter("id", params.get("id"));
			
			roomCategories = q.getResultList();
		} catch (PersistenceException ex) {
			ex.printStackTrace();
			throw new PersistException();
		}
		return roomCategories;
	}

	@Override
	public void update(RoomCategory roomCategory) throws PersistException {
		RoomCategory origRoomCategory = em.find(RoomCategory.class, roomCategory.getId());
		origRoomCategory.setName(roomCategory.getName());
		origRoomCategory.setRooms(roomCategory.getRooms());
	}

	@Override
	public void delete(RoomCategory roomCategory) throws PersistException {
		RoomCategory origRoomCategory = em.find(RoomCategory.class, roomCategory.getId());
		em.remove(origRoomCategory);
	}

}
