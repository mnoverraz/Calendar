package calendar.web.controller;

import java.util.HashMap;

import calendar.core.application.ResourceRegistry;
import calendar.web.renderer.Message;

public abstract class WebController {
	
	protected ResourceRegistry registry;
	
	public WebController(ResourceRegistry registry) {
		this.registry = registry;
	}
	
	public abstract Message create(HashMap<String, String> params);
	public abstract Message read(HashMap<String, String> params);
	public abstract Message update(HashMap<String, String> params);
	public abstract Message delete(HashMap<String, String> params);
}
