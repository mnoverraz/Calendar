package calendar.web.controller;

import java.util.ArrayList;
import java.util.HashMap;

import calendar.core.application.Config;
import calendar.core.application.ResourceRegistry;
import calendar.core.application.utils.DateHelper;
import calendar.core.controller.EventController;
import calendar.core.exception.CoreException;
import calendar.core.exception.TimeSlotException;
import calendar.core.model.Event;
import calendar.core.model.EventDate;


public class WebEventController extends WebController {
	
	private EventController controller;
	
	public WebEventController(ResourceRegistry registry) {
		super(registry);
		this.controller = (EventController) registry.getController("EventController");
	}

	@Override
	public ArrayList<HashMap<String, Object>> create(HashMap<String, String> params) {
		System.out.println("create");
		return null;
	}

	@Override
	public ArrayList<HashMap<String, Object>> read(HashMap<String, String> params) {
		ArrayList<Event> events = null;

		try {
			events = (ArrayList<Event>) controller.read(null);
		} catch (CoreException e) {
			Object detailInformation = e.detailInformation;
			if (e instanceof TimeSlotException) {
				ArrayList<EventDate> eventDates = (ArrayList<EventDate>)detailInformation;
			}
		}

		ArrayList<HashMap<String, Object>> ret = new ArrayList<HashMap<String, Object>>();
				
		for (Event event : events) {
			HashMap<String, Object> eventMap = null;
			for (EventDate eventDate : event.getEventDates()) {
				eventMap = new HashMap<String, Object>();
				eventMap.put("id", event.getId());
				eventMap.put("title", event.getTitle());
				eventMap.put("start", DateHelper.DateToString(eventDate.getStart(), Config.DATE_FORMAT_LONG));
				eventMap.put("end", DateHelper.DateToString(eventDate.getEnd(), Config.DATE_FORMAT_LONG));
				eventMap.put("allDay", eventDate.isAllDay());
				ret.add(eventMap);
			}
		}

		return ret;
	}

	@Override
	public ArrayList<HashMap<String, Object>> update(HashMap<String, String> params) {
		System.out.println("update");
		return null;
	}

	@Override
	public ArrayList<HashMap<String, Object>> delete(HashMap<String, String> params) {
		System.out.println("delete");
		return null;
	}


}
