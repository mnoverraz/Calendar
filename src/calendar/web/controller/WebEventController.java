package calendar.web.controller;

import java.util.ArrayList;
import java.util.HashMap;

import calendar.core.application.Config;
import calendar.core.application.utils.DateHelper;
import calendar.core.controller.EventController;
import calendar.core.model.Event;
import calendar.core.model.EventDate;


public class WebEventController extends WebController {

	@Override
	public ArrayList<HashMap<String, Object>> create(HashMap<String, String> params) {
		return null;
	}

	@Override
	public ArrayList<HashMap<String, Object>> read(HashMap<String, String> params) {
		EventController eventController = new EventController();
		ArrayList<Event> events = eventController.getEvents();
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<HashMap<String, Object>> delete(HashMap<String, String> params) {
		// TODO Auto-generated method stub
		return null;
	}


}
