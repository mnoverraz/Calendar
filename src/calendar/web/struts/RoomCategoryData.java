package calendar.web.struts;

/**
 * Holds the id and the name of a room. It is used to auto populate the options
 * in the select tag included in the 'inputRoom.jsp' and 'inputRoomEdition.jsp' forms.
 * 
 * @author AFFOLTER Nicolas, MEIER Stefan, NOVERRAZ Mathieu
 * @version 2011.06.06
 */
public class RoomCategoryData {
	
	private String roomCategoryId;
    private String roomCategoryName;
	
    public RoomCategoryData(String roomCategoryId, String roomCategoryName) {
		this.roomCategoryId = roomCategoryId;
		this.roomCategoryName = roomCategoryName;
	}

    public String getRoomCategoryId() {
    	return roomCategoryId;
    }
    
    public void setRoomCategoryId(String roomCategoryId) {
    	this.roomCategoryId = roomCategoryId;
    }
    
	public String getRoomCategoryName() {
		return roomCategoryName;
	}

	public void setRoomCategoryName(String roomCategoryName) {
		this.roomCategoryName = roomCategoryName;
	}
    
}
