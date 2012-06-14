package calendar.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import calendar.core.application.Config;
import calendar.core.application.utils.DateHelper;
import calendar.core.controller.EventController;
import calendar.core.controller.RoomController;
import calendar.core.entity.Event;
import calendar.core.entity.EventDate;
import calendar.core.entity.Room;
import calendar.core.exception.CoreException;
import calendar.web.exception.FormNotValidException;
import calendar.web.renderer.ExceptionRenderer;
import calendar.web.renderer.Message;
import calendar.web.utils.FormUtils;

public class WebRoomController extends WebController<RoomController> {

	public WebRoomController(RoomController controller) {
		super(controller);
	}

	@Override
	public Message create(HashMap<String, String> params) {
		Message message = new Message();

		return message;
	}

	@Override
	public Message read(HashMap<String, String> params) {
		ArrayList<Room> rooms = null;
		Message message = new Message();
		message.state = true;

		HashMap<String, Object> filter = new HashMap<String, Object>();

		try {
			if (params != null) {
				if (params.containsKey("id")) {
					int id = Integer.parseInt(params.get("id"));
					filter.put("id", id);
				}
			}
			rooms = (ArrayList<Room>) controller.read(filter);

			HashMap<String, Object> roomMap = null;
			for (Room room : rooms) {
				roomMap = new HashMap<String, Object>();
				roomMap.put("id", room.getId());
				roomMap.put("local", room.getLocal());
				roomMap.put("description", room.getDescription());
				roomMap.put("roomCategory", room.getRoomCategory());
				message.addElementToBody(roomMap);
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
		Message message = new Message();
		return message;
	}

	@Override
	public Message delete(HashMap<String, String> params) {
		Message message = new Message();
		return message;
	}

}
