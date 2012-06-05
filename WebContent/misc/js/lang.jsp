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
lang.put("today", ui.getLangText("today"));
lang.put("month", ui.getLangText("month"));
lang.put("week", ui.getLangText("week"));
lang.put("day", ui.getLangText("day"));
lang.put("day-names", ui.getLangText("day-names"));
lang.put("day-names-short", ui.getLangText("day-names-short"));
lang.put("month-names", ui.getLangText("month-names"));
lang.put("month-names-short", ui.getLangText("month-names-short"));

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
