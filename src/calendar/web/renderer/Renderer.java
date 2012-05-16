package calendar.web.renderer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import calendar.core.application.Config;
import calendar.core.application.utils.DateHelper;
import calendar.core.model.Event;
import calendar.core.model.EventDate;

public class Renderer {
	public Renderer() {
		
	}
	
	public static String toJSON(ArrayList<HashMap<String, Object>> content) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("[");
		
		if (content != null) {
			for (int i = 0; i < content.size(); i++) {
				HashMap<String, Object> inner = content.get(i);
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
				
				if (content.size() - (i+1) > 0) {
					sb.append(",");
				}
			}
		}
		sb.append("]");
		
		return sb.toString();
	}
}
