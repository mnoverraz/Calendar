package calendar.core.application;

import java.util.HashMap;

import calendar.core.controller.Controller;

public class ResourceRegistry {
	private HashMap<String, Controller<?>> controllers;
	
	public ResourceRegistry() {
		controllers = new HashMap<String, Controller<?>>();
	}
	
	public void registerController(String name, Controller<?> controller) {
		controllers.put(name, controller);
	}
	
	public void unregisterController(Controller<?> controller) {
		if (controllers.containsValue(controller)) {
			controllers.remove(controller);
		}
	}
	
	public Controller<?> getController(String name) {
		if (controllers.containsKey(name))
			return controllers.get(name);
		else 
			return null;
	}
	
}
