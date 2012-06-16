package calendar.web.renderer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

/**
 * Renders a message to JSON or XML
 * Uses recursive methods in order to walk trough all elements
 * in the tree
 * Renderers access is limited to package
 * 
 * @author AFFOLTER Nicolas, MEIER Stefan, NOVERRAZ Mathieu
 * @version 2011.06.06
 */
class Renderer {
	@SuppressWarnings("unchecked")
	/**
	 * Renders a messages content to JSON
	 * @param key
	 * @param value
	 * @return String
	 */
	protected static String toJSON (String key, Object value) {
		StringBuilder sb = new StringBuilder();
		/*
		 * If the value is null, there is no child anymore
		 */
		if (value != null) {
			/*
			 * If a name is specified for the value, it is shown
			 */
			if (null != key) {
				sb.append("\"" + key + "\":");

			}
			/*
			 * If the value is a HashMap we know there can be a key and a value in the
			 * data
			 * Braces are used to identify this kind of data
			 */
			if (value instanceof HashMap) {
				sb.append("{");
				Iterator<Entry<String, Object>> it = ((HashMap<String, Object>)value).entrySet().iterator();
				while (it.hasNext()) {
					String sKey = it.next().getKey();
					Object sValue = ((HashMap<String, Object>)value).get(sKey);
					sb.append(toJSON(sKey, sValue));
					/*
					 * Adds a comma only if there is a next element as specified by JSON format
					 */
					if (it.hasNext())
						sb.append(",");
				}

				sb.append("}");
			}
			/*
			 * If the value is an ArrayList no name can be specified for the element
			 * 
			 */
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
				/*
				 * If the value is a boolean, no quotation mark is used
				 */
				if (value instanceof Boolean)
					sb.append(value);
				else
					sb.append("\"" +value + "\"");
			}
		}
		
		return sb.toString();
	}
	
	/**
	 * Returns a messages content to XML
	 * (Not implemented and a blank string is returned)
	 * @param key
	 * @param value
	 * @return
	 */
	protected static String toXML (String key, Object value) {
		StringBuilder sb = new StringBuilder();
		return sb.toString();
	}
}
