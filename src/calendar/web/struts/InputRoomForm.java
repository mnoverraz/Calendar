package calendar.web.struts;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import calendar.core.ejb.entity.RoomCategory;
import calendar.core.exception.CoreException;
import calendar.management.controller.RoomCategoryController;
import calendar.management.exception.SystemException;

/**
 * This class is an ActionForm bean that holds the room properties
 * 
 * It implements the validate and reset methods from the ActionForm superclass.
 * 
 * @author AFFOLTER Nicolas, MEIER Stefan, NOVERRAZ Mathieu
 * @version 2011.06.06
 */
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
	
	/**
	 * Gets the array list of all the room categories through the room category controller
	 * and populates the room category list with its values.
	 * 
	 * @param mapping
	 * @param request
	 * @return void
	 * @throws CoreException
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		
		ServletContext context = servlet.getServletConfig().getServletContext();
		RoomCategoryController roomCategoryController = (RoomCategoryController) context.getAttribute("roomCategoryController");
		
		ArrayList<RoomCategory> roomCategories = null;
		ArrayList<RoomCategoryData> roomCategoryList = new ArrayList<RoomCategoryData>();
		
		try {
			roomCategories = roomCategoryController.read(null);			
		} catch (CoreException e) {
			SystemException se = new SystemException();
			se.detailInformation = e;
		}
		
		for (RoomCategory rc : roomCategories) {
			roomCategoryList.add(new RoomCategoryData(String.valueOf(rc.getId()), rc.getName()));
		}
		
		setRoomCategoryList(roomCategoryList);
		
	}
	
	
	/**
	 * Performs a validation check on the 'local' and 'roomCategory' properties.
	 * 
	 * @param mapping
	 * @param request
	 * @return ActionErrors
	 */
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		
		ActionErrors errors = new ActionErrors();
		
		if (getLocal() == null || getLocal().length() < 1) {
            errors.add("local", new ActionMessage("error.local.required"));
        } else if (getLocal().length() > 255 ) {
        	errors.add("local", new ActionMessage("error.local.maxchars"));
        }
		
		if (getName().length() > 255 ) 
        	errors.add("name", new ActionMessage("error.name.maxchars"));
        
		if (getDescription().length() > 255 ) 
        	errors.add("description", new ActionMessage("error.description.maxchars"));
		
		if (getRoomCategory().equals("0"))
			errors.add("roomCategory", new ActionMessage("error.roomCategory.required"));
		
		return errors;
	}
	
}
