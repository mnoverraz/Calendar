package calendar.web.struts;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;


public class RoomForm extends ActionForm {

	private String id;
	private String local;
	private String name;
	private String description;
	private String roomCategory;
	
	public RoomForm() {
	  super();
	}

	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        
             
//        if ((password == null) || (password.length() < 3)) 
//        {
//          errors.add("applicationConfig.menu.txt.manageUsers.UpdateNOK", new ActionMessage("applicationConfig.menu.txt.manageUsers.UpdateNOK"));
//        }
 
        System.out.print(errors); 
        
        return errors;
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
	  
}
