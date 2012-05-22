package calendar.web.controller;

import java.util.HashMap;

import calendar.core.controller.Controller;
import calendar.web.renderer.Message;

public abstract class WebController<C extends Controller> {
	
	protected C controller;
	
	public WebController (C controller) {
		this.controller = controller;
	}
	
	public abstract Message create(HashMap<String, String> params);
	public abstract Message read(HashMap<String, String> params);
	public abstract Message update(HashMap<String, String> params);
	public abstract Message delete(HashMap<String, String> params);
}
