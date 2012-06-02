package calendar.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import calendar.core.application.Config;
import calendar.core.application.utils.DateHelper;
import calendar.core.controller.EventController;
import calendar.core.entity.Event;
import calendar.core.entity.EventDate;
import calendar.core.exception.CoreException;
import calendar.core.exception.TimeSlotException;
import calendar.web.exception.FormNotValidException;
import calendar.web.renderer.ExceptionRenderer;
import calendar.web.renderer.Message;
import calendar.web.utils.FormUtils;

public class WebEventController extends WebController<EventController> {

	public WebEventController(EventController controller) {
		super(controller);
	}

	@Override
	public Message create(HashMap<String, String> params) {
		Event event = null;
		Message message = new Message();

		if (params != null) {
			String date = null;
			String startH = null;
			String startM = null;
			String endH = null;
			String endM = null;
			String allDay = null;
			String repeatMode = null;
			String repeatEnd = null;
			String title = null;
			String description = null;

			Iterator<Entry<String, String>> it = params.entrySet().iterator();
			
			//System.out.println(params.get("startH"));
			try {
				while (it.hasNext()) {
					Object key = it.next().getKey();
					Object value = params.get(key);

					if ("startH".equals(key))
						startH = (String) value;
					if ("endH".equals(key))
						endH = (String) value;
					if ("startM".equals(key))
						startM = (String) value;
					if ("endM".equals(key))
						endM = (String) value;
					if ("date".equals(key))
						date = (String) value;
					if ("allDay".equals(key))
						allDay = (String) value;
					if ("repeatMode".equals(key))
						repeatMode = (String) value;
					if ("repeatEnd".equals(key))
						repeatEnd = (String) value;
					if ("description".equals(key))
						description = (String) value;
					if ("title".equals(key)) 
						title = (String)value;
				}
				
				try {
				event = FormUtils.createEventFromForm(date, startH, startM, endH, endM, allDay, repeatMode, repeatEnd, title, description);
					controller.create(event);
					HashMap<String, Object> eventMap = null;
					for (EventDate eventDate : event.getEventDates()) {
						eventMap = new HashMap<String, Object>();
						eventMap.put("id", event.getId());
						eventMap.put("title", event.getTitle());
						eventMap.put("start", DateHelper.DateToString(
								eventDate.getStart(), Config.DATE_FORMAT_LONG));
						eventMap.put("end", DateHelper.DateToString(
								eventDate.getEnd(), Config.DATE_FORMAT_LONG));
						eventMap.put("allDay", eventDate.isAllDay());
	
						message.addElementToBody(eventMap);
					}
				}
				catch (FormNotValidException fe) {
					message.state = false;
					ExceptionRenderer exRenderer = new ExceptionRenderer(fe);
					message = exRenderer.getMessage();
				}
				

			} catch (Exception e) {
				message.state = false;
				ExceptionRenderer exRenderer = new ExceptionRenderer(e);
				message = exRenderer.getMessage();
			}
		}

		return message;
	}

	@Override
	public Message read(HashMap<String, String> params) {
		ArrayList<Event> events = null;
		Message message = new Message();
		message.state = true;

		try {
			events = (ArrayList<Event>) controller.read(null);
		} catch (TimeSlotException e) {
			Object detailInformation = e.detailInformation;
			@SuppressWarnings("unchecked")
			ArrayList<EventDate> eventDates = (ArrayList<EventDate>) detailInformation;
			message.state = false;

		} catch (CoreException e) {
			message.state = false;
			ExceptionRenderer exRenderer = new ExceptionRenderer(e);
			message = exRenderer.getMessage();
		}

		for (Event event : events) {
			HashMap<String, Object> eventMap = null;
			for (EventDate eventDate : event.getEventDates()) {
				eventMap = new HashMap<String, Object>();
				eventMap.put("id", event.getId());
				eventMap.put("title", event.getTitle());
				eventMap.put("start", DateHelper.DateToString(
						eventDate.getStart(), Config.DATE_FORMAT_LONG));
				eventMap.put("end", DateHelper.DateToString(eventDate.getEnd(),
						Config.DATE_FORMAT_LONG));
				eventMap.put("allDay", eventDate.isAllDay());

				message.addElementToBody(eventMap);
			}
		}

		return message;
	}

	@Override
	public Message update(HashMap<String, String> params) {
		Message message = new Message();
		return message;
	}

	@Override
	public Message delete(HashMap<String, String> params) {
		Message message = new Message();
		return message;
	}

}
