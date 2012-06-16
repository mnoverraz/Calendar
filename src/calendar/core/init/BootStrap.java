package calendar.core.init;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import calendar.management.controller.EventController;
import calendar.management.controller.RoomCategoryController;
import calendar.management.controller.RoomController;

/**
 * Loads and makes available ResourceRegistry
 * 
 * @author AFFOLTER Nicolas, MEIER Stefan, NOVERRAZ Mathieu
 * @version 2011.06.06
 */
public class BootStrap {
	public static ResourceRegistry ResourceRegistry;
		
	public static void init() throws NamingException {
		ResourceRegistry = new ResourceRegistry();
		ResourceRegistry.registerController("EventController", new EventController(new InitialContext()));
		ResourceRegistry.registerController("RoomController", new RoomController(new InitialContext()));
		ResourceRegistry.registerController("RoomCategoryController", new RoomCategoryController(new InitialContext()));
	}
}
