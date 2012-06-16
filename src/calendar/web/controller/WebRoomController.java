package calendar.web.controller;

import java.util.ArrayList;
import java.util.HashMap;

import calendar.core.ejb.entity.Room;
import calendar.core.exception.CoreException;
import calendar.management.controller.RoomController;
import calendar.web.renderer.ExceptionRenderer;
import calendar.web.renderer.Message;

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
					long id = Integer.parseInt(params.get("id"));
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
				roomMap.put("roomCategory", room.getRoomCategory().getName());
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
