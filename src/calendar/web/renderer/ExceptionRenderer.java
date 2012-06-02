package calendar.web.renderer;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import calendar.core.application.Config;
import calendar.core.application.utils.DateHelper;
import calendar.core.entity.EventDate;
import calendar.core.exception.CoreException;
import calendar.core.exception.TimeSlotException;
import calendar.web.exception.FormNotValidException;

public class ExceptionRenderer {
	
	private Exception ex;
	
	public ExceptionRenderer(Exception ex) {
		this.ex = ex;
	}
	
	public Message getMessage() {
		Message message = new Message();
		message.state = false;
		HashMap<String, Object> content = new HashMap<String, Object>();
		ArrayList<HashMap<String, Object>> description = new ArrayList<HashMap<String, Object>>();
		
		String exceptionName = ex.getClass().getSimpleName();
		
		
		if (ex instanceof TimeSlotException) {
			TimeSlotException ts = (TimeSlotException)ex;
			@SuppressWarnings("unchecked")
			ArrayList<EventDate> eventDates = (ArrayList<EventDate>) ts.detailInformation;

			HashMap<String, Object> eventMap = null;
			message.state = false;
			for (EventDate eventDate : eventDates) {
				eventMap = new HashMap<String, Object>();
				eventMap.put("start", DateHelper.DateToString(eventDate.getStart(), Config.DATE_FORMAT_LONG));
				eventMap.put("end", DateHelper.DateToString(eventDate.getEnd(), Config.DATE_FORMAT_LONG));
				description.add(eventMap);
				
			}
			content.put(exceptionName, description);
		}
		else if (ex instanceof FormNotValidException) {
			FormNotValidException fe = (FormNotValidException)ex;
			HashMap<String, Object> errors = (HashMap<String, Object>) fe.detailInformation;
			content.put("FormNotValidException", errors);
		}
		else {
			StringBuilder sb = new StringBuilder();
			StringWriter sw = new StringWriter();
			ex.printStackTrace(new PrintWriter(sw));
			sb.append("Exception:\t" + ex.getClass());
			sb.append("Message\t" + ex.getMessage());
			sb.append("StackTrace\t" + sw.toString());
			System.out.println(sb.toString());
			content.put(exceptionName, "For security reasons, no details are shown. Please consult servers log file for further detail.");
		}

		message.addElementToBody(content);
		return message;
	}
}
