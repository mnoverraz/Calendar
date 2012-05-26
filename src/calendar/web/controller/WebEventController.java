package calendar.web.controller;

import java.util.ArrayList;
import java.util.HashMap;

import calendar.core.application.Config;
import calendar.core.application.utils.DateHelper;
import calendar.core.controller.EventController;
import calendar.core.exception.CoreException;
import calendar.core.exception.TimeSlotException;
import calendar.core.model.Event;
import calendar.core.model.EventDate;
import calendar.web.renderer.Message;


public class WebEventController extends WebController<EventController> {
	

	public WebEventController(EventController controller) {
		super(controller);
	}

	@Override
	public Message create(HashMap<String, String> params) {
		System.out.println("create");
		return null;
	}

	@Override
	public Message read(HashMap<String, String> params) {
		ArrayList<Event> events = null;
		Message message = new Message();
		message.state = true;
		
		
		try {
			events = (ArrayList<Event>) controller.read(null);
		} 
		catch (TimeSlotException e) {
			Object detailInformation = e.detailInformation;
			@SuppressWarnings("unchecked")
			ArrayList<EventDate> eventDates = (ArrayList<EventDate>)detailInformation;
			message.state = false;

		}
		catch (CoreException e) {
			
		}
				
		for (Event event : events) {
			HashMap<String, Object> eventMap = null;
			for (EventDate eventDate : event.getEventDates()) {
				eventMap = new HashMap<String, Object>();
				eventMap.put("id", event.getId());
				eventMap.put("title", event.getTitle());
				eventMap.put("start", DateHelper.DateToString(eventDate.getStart(), Config.DATE_FORMAT_LONG));
				eventMap.put("end", DateHelper.DateToString(eventDate.getEnd(), Config.DATE_FORMAT_LONG));
				eventMap.put("allDay", eventDate.isAllDay());
				
				message.addElementToBody(eventMap);
			}
		}

		return message;
	}

	@Override
	public Message update(HashMap<String, String> params) {
		System.out.println("update");
		return null;
	}

	@Override
	public Message delete(HashMap<String, String> params) {
		System.out.println("delete");
		return null;
	}


}
