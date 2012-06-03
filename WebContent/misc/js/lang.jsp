<%@ page contentType="text/javascript; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<%@ page import="java.util.HashMap"%> 
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.Map.Entry"%>

<jsp:useBean id="ui" class="calendar.web.bean.UIBean" scope="session" />

<%
HashMap<String, String> lang = new HashMap<String, String>();
lang.put("mont-1-full", ui.getLangText("month-1-full"));
lang.put("mont-2-full", ui.getLangText("month-2-full"));
lang.put("calendar-event-title", ui.getLangText("calendar-event-title"));
lang.put("calendar-event-date", ui.getLangText("calendar-event-date"));
lang.put("calendar-event-wholeDay", ui.getLangText("calendar-event-wholeDay"));
lang.put("calendar-event-start", ui.getLangText("calendar-event-start"));
lang.put("calendar-event-end", ui.getLangText("calendar-event-end"));
lang.put("calendar-event-repeat", ui.getLangText("calendar-event-repeat"));
lang.put("calendar-event-never", ui.getLangText("calendar-event-never"));
lang.put("calendar-event-daily", ui.getLangText("calendar-event-daily"));
lang.put("calendar-event-weekly", ui.getLangText("calendar-event-weekly"));
lang.put("calendar-event-halfMonthly", ui.getLangText("calendar-event-halfMonthly"));
lang.put("calendar-event-monthly", ui.getLangText("calendar-event-monthly"));
lang.put("calendar-event-yearly", ui.getLangText("calendar-event-yearly"));
lang.put("today", ui.getLangText("today"));
lang.put("today", ui.getLangText("today"));

/*
Generate JavaScript Array
*/
Iterator<Entry<String, String>> it = lang.entrySet().iterator();

out.println("resourceBundle = new Array();");
while (it.hasNext()) {
	String key = it.next().getKey();
	String value = lang.get(key);
	
	out.println("resourceBundle[\"" + key + "\"] = \"" + value + "\";");;
}
%>
