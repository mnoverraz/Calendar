package calendar.web.controller;

import java.util.HashMap;

import calendar.management.controller.Controller;
import calendar.web.renderer.Message;
/**
 * Interfaces application controller and user interaction
 * Specifies general operation for all Web controllers
 * 
 * @author AFFOLTER Nicolas, MEIER Stefan, NOVERRAZ Mathieu
 * @version 2011.06.06
 */
public abstract class WebController<C extends Controller<?>> {
	
	/*
	 * Generic controller, once specified
	 * it will be available to child classes
	 */
	protected C controller;
	
	public WebController (C controller) {
		this.controller = controller;
	}
	
	/**
	 * Creates an object corresponding to the parameter specified
	 * @param params (Contains information to build the controlled object type)
	 * @return Message
	 * @see calendar.core.controller.Controller
	 */
	public abstract Message create(HashMap<String, String> params);
	/**
	 * Gets an object specified by the filter
	 * @param filter (permits to filter data in order to retrieve only a port of it)
	 * @return Message
	 * @see calendar.core.controller.Controller
	 */
	public abstract Message read(HashMap<String, String> filter);
	/**
	 * Updates data specified by the parameters
	 * @param params (Contains information to build the controlled object type)
	 * @return Message
	 * @see calendar.core.controller.Controller
	 */
	public abstract Message update(HashMap<String, String> params);
	/**
	 * Deletes data specified by the parameters
	 * @param params (Contains information to build the controlled object type)
	 * @return Message
	 * @see calendar.core.controller.Controller
	 */
	public abstract Message delete(HashMap<String, String> params);
}
