package calendar.web.controller;

import java.util.ArrayList;
import java.util.HashMap;

import calendar.core.application.ResourceRegistry;

public abstract class WebController {
	
	protected ResourceRegistry registry;
	
	public WebController(ResourceRegistry registry) {
		this.registry = registry;
	}
	
	public abstract ArrayList<HashMap<String, Object>> create(HashMap<String, String> params);
	public abstract ArrayList<HashMap<String, Object>> read(HashMap<String, String> params);
	public abstract ArrayList<HashMap<String, Object>> update(HashMap<String, String> params);
	public abstract ArrayList<HashMap<String, Object>> delete(HashMap<String, String> params);
}
