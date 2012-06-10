package calendar.test;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.junit.Test;

import calendar.core.entity.Event;
import calendar.core.exception.SystemException;
import calendar.web.exception.FormNotValidException;
import calendar.web.utils.FormUtils;

public class FormUtilsTest {

	@Test
	public void testFormUtilsValidate() {
		/*
		 * allDay	on
date	2012-06-22
description	
endH	0
endM	0
id	id
repeatEnd	2012-06-22
repeatMode	n
startH	0
startM	0
title	adsf
		 */
		Event event = null;
		String fId = null;
		String fDate = "2012-06-22";
		String fStartH = "0";
		String fStartM = "0";
		String fEndH = "0";
		String fEndM = "0";
		String fAllDay = "on";
		String fRepeatMode = "n";
		String fRepeatEnd = "2012-06-22";
		String fTitle = "title";
		String fDescription = "";
		
		try {
			event = FormUtils.createEventFromForm(fId, fDate, fStartH, fStartM, fEndH, fEndM, fAllDay, fRepeatMode, fRepeatEnd, fTitle, fDescription);
		} catch (FormNotValidException e) {
			HashMap<String, Boolean> validation = (HashMap<String, Boolean>)e.detailInformation;
			Iterator<Entry<String, Boolean>> it = validation.entrySet().iterator();


				while (it.hasNext()) {
					String key = it.next().getKey();
					Boolean value = validation.get(key);
					System.out.println("key: " + key + " value: " + value);
				}
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(event);
		
		//{"success":false,"content":[{"FormNotValidException":{"title":false,"startH":false,"repeatMode":false,"allDay":false,"endH":false,"description":false,"repeatEnd":true,"endM":false,"date":false,"startM":false}}]}
	}

}
