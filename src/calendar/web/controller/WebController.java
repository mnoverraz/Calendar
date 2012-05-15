package calendar.web.controller;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class WebController {
	public abstract ArrayList<HashMap<String, Object>> create(HashMap<String, String> params);
	public abstract ArrayList<HashMap<String, Object>> read(HashMap<String, String> params);
	public abstract ArrayList<HashMap<String, Object>> update(HashMap<String, String> params);
	public abstract ArrayList<HashMap<String, Object>> delete(HashMap<String, String> params);
}
