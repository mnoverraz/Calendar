package calendar.web.renderer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class Message {
	public boolean state = true;
	
	private ArrayList<HashMap<String, Object>> message;
	
	public Message() {
		message = new ArrayList<HashMap<String, Object>>();
	}
	
	public void addElementToBody(HashMap<String, Object> element) {
		message.add(element);
	}
	
	public ArrayList<HashMap<String, Object>> getRawContent() {
		return message;
	}
	
	public String toJSON(boolean showState) {

		StringBuilder sb = new StringBuilder();
		
		sb.append("[");
		
		if (showState)
			sb.append("\"success\":" + state);
		
		if (message != null) {
			if (showState)
				sb.append(",");
			for (int i = 0; i < message.size(); i++) {
				HashMap<String, Object> inner = message.get(i);
				Iterator<Entry<String, Object>> it = inner.entrySet().iterator();
				sb.append("{");
	
				
				while (it.hasNext()) {
					Object key = it.next().getKey();
					Object value = inner.get(key);
					
					sb.append("\"" + key + "\":");
					
					if (value instanceof Boolean)
						sb.append(value);
					else
						sb.append("\"" + value + "\"");
					
					if (it.hasNext())
						sb.append(",");
				}
				sb.append("}");
				
				if (message.size() - (i+1) > 0) {
					sb.append(",");
				}
			}
		}
		sb.append("]");
		
		return sb.toString();
	}
}
