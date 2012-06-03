<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<jsp:useBean id="ui" class="calendar.web.bean.UIBean" scope="session" />
<% 
String lang = request.getParameter("lang");
if (lang != null && !lang.equals(ui.getLang())) 
	ui.setLang(lang);


 %>
<html>
<head>
<title>CALENDAR</title>
<meta content="" name="keywords">
<meta content="" name="description">
<meta content="0" http-equiv="expires">
<meta content="no-cache" http-equiv="pragma">
<meta content="text/html; charset=UTF-8" http-equiv="Content-Type">
<script type='text/javascript' src='misc/js/lang.jsp'></script>
</head>
<body>

<%=ui.getLangText("month-1-full") %>
</body>
</html>