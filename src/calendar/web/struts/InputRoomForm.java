package calendar.web.struts;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;


@SuppressWarnings("serial")
public class InputRoomForm extends ActionForm {

	private String id;
	private String local;
	private String name;
	private String description;
	private String roomCategory;
	private ArrayList<RoomCategoryData> roomCategoryList;

	public InputRoomForm() {
	  super();
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRoomCategory() {
		return roomCategory;
	}

	public void setRoomCategory(String roomCategory) {
		this.roomCategory = roomCategory;
	}
	  
	public ArrayList<RoomCategoryData> getRoomCategoryList() {
		return roomCategoryList;
	}
	
	public void setRoomCategoryList(ArrayList<RoomCategoryData> roomCategoryList) {
		this.roomCategoryList = roomCategoryList;
	}
	
//	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
//		
//		ActionErrors errors = new ActionErrors();
//		
//		if ( getLocal() == null || getLocal().length() < 1 ) {
//			errors.add("local", new ActionMessage("local.error"));
//		}
//		
//		System.out.print(errors); 
//		
//		return errors;
//	}
	
}
