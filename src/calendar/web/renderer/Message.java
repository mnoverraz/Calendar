package calendar.web.renderer;

import java.util.ArrayList;
/**
 * Translates all information in this application to a format unsterstandable by
 * a web client
 * Messages can be returned as XML or JSON
 * All messages are formated as a hierarchical data tree :
 * ROOT
 * 	CHILD
 * 		CHILD OF CHILD
 * 	CHILD
 * 
 * By convention, all elements except the ROOT can have an optional name
 * If there is a name specified, information will be added
 * as HashMap<String, Object>, otherwise an ArrayList<Object> is used
 * 
 * @author AFFOLTER Nicolas, MEIER Stefan, NOVERRAZ Mathieu
 * @version 2011.06.06
 */
public class Message {
	/*
	 * Indicates false if an error has occured
	 */
	public boolean state = true;
	
	/*
	 * ROOT has no name, therefore an ArrayList is used
	 */
	private ArrayList<Object> message;
	
	public Message() {
		message = new ArrayList<Object>();
	}
	
	/*
	 * Adds information to the ROOT
	 */
	public void addElementToBody(Object element) {
		message.add(element);
	}
	
	/**
	 * Returns the message in JSON format
	 * @param showState
	 * @return String
	 */
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
	
	/**
	 * Returns the message in XML format
	 * 
	 * (Not implemented and a blank string is returned)
	 * @param showState
	 * @return String
	 */
	public String toXML(boolean showState) {
		StringBuilder sb = new StringBuilder();
		return sb.toString();
	}
}
