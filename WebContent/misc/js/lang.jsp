<%@ page contentType="text/javascript; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<%@ page import="java.util.HashMap"%> 
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.Map.Entry"%>

<jsp:useBean id="ui" class="calendar.ui.bean.UIBean" scope="session" />

<%
HashMap<String, String> lang = new HashMap<String, String>();
lang.put("mont-1-full", ui.getLangText("month-1-full"));
lang.put("mont-2-full", ui.getLangText("month-2-full"));
lang.put("today", ui.getLangText("today"));
lang.put("month", ui.getLangText("month"));
lang.put("week", ui.getLangText("week"));
lang.put("day", ui.getLangText("day"));
lang.put("day-names", ui.getLangText("day-names"));
lang.put("day-names-short", ui.getLangText("day-names-short"));
lang.put("month-names", ui.getLangText("month-names"));
lang.put("month-names-short", ui.getLangText("month-names-short"));

//format
lang.put("format-title-month", ui.getLangText("format-title-month"));
lang.put("format-title-week", ui.getLangText("format-title-week"));
lang.put("format-title-day", ui.getLangText("format-title-day"));
lang.put("format-column-month", ui.getLangText("format-column-month"));
lang.put("format-column-week", ui.getLangText("format-column-week"));
lang.put("format-column-day", ui.getLangText("format-column-day"));

lang.put("application-title", ui.getLangText("application-title"));


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
