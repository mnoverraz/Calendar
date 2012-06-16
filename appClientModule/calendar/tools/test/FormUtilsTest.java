package calendar.tools.test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.junit.Test;

import calendar.core.ejb.entity.Event;
import calendar.management.exception.SystemException;
import calendar.web.exception.FormNotValidException;
import calendar.web.renderer.form.EventFormUtils;

public class FormUtilsTest {

	@SuppressWarnings("unchecked")
	@Test
	public void testFormUtilsValidate() {
		Event event = null;

		String fId = "";
		String fDate = "2012-06-22";
		String fStartH = "00";
		String fStartM = "00";
		String fEndH = "00";
		String fEndM = "00";
		String fAllDay = "on";
		String fRepeatMode = "d";
		String fRepeatEnd = "2012-06-30";
		String fTitle = "repeatDaily";
		String fDescription = "";

		try {
			event = EventFormUtils.createEventFromForm(fId, fDate, fStartH, fStartM,
					fEndH, fEndM, fAllDay, fRepeatMode, fRepeatEnd, fTitle,
					fDescription);
		} catch (FormNotValidException e) {
			HashMap<String, Boolean> validation = (HashMap<String, Boolean>) e.detailInformation;
			Iterator<Entry<String, Boolean>> it = validation.entrySet()
					.iterator();

			while (it.hasNext()) {
				String key = it.next().getKey();
				Boolean value = validation.get(key);
				System.out.println("key: " + key + " value: " + value);
			}
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNotNull(event);
	}
	
	
}
