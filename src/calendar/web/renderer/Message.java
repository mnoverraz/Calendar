package calendar.web.renderer;

import java.util.ArrayList;
import java.util.HashMap;

public class Message {
	public boolean state = true;
	
	private ArrayList<HashMap<String, Object>> message;
	
	public Message() {
		message = new ArrayList<HashMap<String, Object>>();
	}
	
	public void addElementToBody(HashMap<String, Object> element) {
		message.add(element);
	}
	
	public String toJSON(boolean showState) {
		StringBuilder sb = new StringBuilder();
		
		if (showState) {
		sb.append("{");
		sb.append("\"success\":" + state);
		sb.append(",");
		sb.append(Renderer.toJSON("content", message));
		sb.append("}");
		}
		else {
			sb.append(Renderer.toJSON(null, message));
		}

		return sb.toString();
	}
}
