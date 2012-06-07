package calendar.core.application;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import calendar.core.controller.EventController;

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
	}
}
