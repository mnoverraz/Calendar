package calendar.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import calendar.core.ejb.entity.Event;
import calendar.core.ejb.entity.EventDate;
import calendar.core.exception.CoreException;
import calendar.core.init.Config;
import calendar.management.controller.EventController;
import calendar.tools.utils.DateHelper;
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
			String id = null;
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

			try {
				while (it.hasNext()) {
					String key = it.next().getKey();
					String value = params.get(key);

					if ("id".equals(key))
						id = value;
					if ("startH".equals(key))
						startH = value;
					if ("endH".equals(key))
						endH = value;
					if ("startM".equals(key))
						startM = value;
					if ("endM".equals(key))
						endM = value;
					if ("date".equals(key))
						date = value;
					if ("allDay".equals(key))
						allDay = value;
					if ("repeatMode".equals(key))
						repeatMode = value;
					if ("repeatEnd".equals(key))
						repeatEnd = value;
					if ("description".equals(key))
						description = value;
					if ("title".equals(key))
						title = value;
				}

				try {
					event = FormUtils.createEventFromForm(id, date, startH,
							startM, endH, endM, allDay, repeatMode, repeatEnd,
							title, description);
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
						eventMap.put("description", event.getDescription());
						eventMap.put("repeatMode", event.getMode());
						eventMap.put("repeatEnd", event.getRepeatEnd());

						message.addElementToBody(eventMap);
					}
				} catch (FormNotValidException fe) {
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

		HashMap<String, Object> filter = new HashMap<String, Object>();

		try {
			if (params != null) {
				if (params.containsKey("id")) {
					long id = Long.parseLong(params.get("id"));
					filter.put("id", id);
				}
				if (params.containsKey("start")) {
					long timeStamp = Long.parseLong(params.get("start"));

					Date date = new Date(timeStamp * 1000);
					filter.put("start", date);
				}
				if (params.containsKey("end")) {
					long timeStamp = Long.parseLong(params.get("end"));

					Date date = new Date(timeStamp * 1000);
					filter.put("end", date);
				}
			}
			events = (ArrayList<Event>) controller.read(filter);

			for (Event event : events) {
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
					eventMap.put("description", event.getDescription());
					eventMap.put("repeatMode", event.getMode());
					if (event.getRepeatEnd() == null) 
						eventMap.put("repeatEnd", "");
					else
						eventMap.put("repeatEnd", event.getRepeatEnd());
					
					message.addElementToBody(eventMap);
				}
			}
		} catch (CoreException e) {
			message.state = false;
			ExceptionRenderer exRenderer = new ExceptionRenderer(e);
			message = exRenderer.getMessage();
		}
		return message;
	}

	@Override
	public Message update(HashMap<String, String> params) {
		Message message = this.create(params);
		return message;
	}

	@Override
	public Message delete(HashMap<String, String> params) {
		Event event = null;
		Message message = new Message();

		if (params != null) {

			if (params.containsKey("id")) {
				try {
					long id = Long.parseLong(params.get("id"));
					event = new Event(id);

					controller.delete(event);
					
					message.addElementToBody(event);

				} catch (Exception e) {
					message.state = false;
					ExceptionRenderer exRenderer = new ExceptionRenderer(e);
					message = exRenderer.getMessage();
				}
			}
		}

		return message;
	}

}
