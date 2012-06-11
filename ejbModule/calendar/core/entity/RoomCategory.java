package calendar.core.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table (name="room_categories")
public class RoomCategory implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Column
	private String name;
	@OneToMany(mappedBy="roomCategory", fetch = FetchType.EAGER, cascade={CascadeType.MERGE})
	private List<Room> rooms;
	
	public RoomCategory() {
		rooms = new ArrayList<Room>();
	}

	public RoomCategory(long id, String name) {
		rooms = new ArrayList<Room>();
		this.id = id;
		this.name = name;
	}
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Room> getRooms() {
		return rooms;
	}
	
	public void addRoom(Room room) {
		room.setRoomCategory(this);
		rooms.add(room);
	}

	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}

}
