package calendar.web.struts;

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
