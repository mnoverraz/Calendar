package calendar.core.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import calendar.core.application.Config;
import calendar.core.application.utils.DateHelper;
import calendar.core.exception.CoreException;
import calendar.core.exception.TimeSlotException;
import calendar.core.model.Event;
import calendar.core.model.EventDate;


public class EventController extends Controller<Event> {	
	@Override
	public void create(Event event) throws CoreException {
		TimeSlotException timeSlotException = new TimeSlotException();
		timeSlotException.detailInformation = new ArrayList<EventDate>();
		throw timeSlotException;
	}

	@Override
	public ArrayList<Event> read(HashMap<String, Object> params) throws CoreException {
		ArrayList<Event> events = new ArrayList<Event>();
		ArrayList<EventDate> eventDates = new ArrayList<EventDate>();
		Calendar calendar = Calendar.getInstance();

		Date date2 = null;
		
		try {
			date2 = DateHelper.StringToDate("2012-05-15 10:00", Config.DATE_FORMAT_LONG);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		eventDates.add(new EventDate(calendar.getTime(), date2));
		eventDates.add(new EventDate(DateHelper.getToday(), DateHelper.getToday()));
		
		Event event1 = new Event(1, eventDates, "reccurent 1", "description");
		
		events.add(event1);
		
		return events;
	}

	@Override
	public void update(Event event) throws CoreException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Event event) throws CoreException {
		// TODO Auto-generated method stub
		
	}
}
