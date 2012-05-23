package calendar.core.application;

import calendar.core.controller.EventController;


public class BootStrap {
	public static ResourceRegistry ResourceRegistry;
		
	public static void init() {
		ResourceRegistry = new ResourceRegistry();
		ResourceRegistry.registerController("EventController", new EventController());
	}
	
	
}
