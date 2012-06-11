package calendar.web.renderer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class Renderer {
	@SuppressWarnings("unchecked")
	public static String toJSON (String key, Object value) {
		StringBuilder sb = new StringBuilder();
		if (value != null) {
			if (null != key) {
				sb.append("\"" + key + "\":");

			}
			if (value instanceof HashMap) {
				sb.append("{");
				Iterator<Entry<String, Object>> it = ((HashMap<String, Object>)value).entrySet().iterator();
				while (it.hasNext()) {
					String sKey = it.next().getKey();
					Object sValue = ((HashMap<String, Object>)value).get(sKey);
					sb.append(toJSON(sKey, sValue));
					if (it.hasNext())
						sb.append(",");
				}

				sb.append("}");
			}
			else if (value instanceof ArrayList) {
				sb.append("[");
				int size = 0;
				ArrayList<Object> elements = (ArrayList<Object>)value;
				for (Object o : elements) {
					sb.append(toJSON(null, o));
					if (elements.size() - (size+1) > 0) {
						sb.append(",");
					}
					size++;
				}
				sb.append("]");
			}
			else {				
				if (value instanceof Boolean)
					sb.append(value);
				else
					sb.append("\"" +value + "\"");
			}
		}
		
		return sb.toString();
	}
}
