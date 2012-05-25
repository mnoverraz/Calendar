<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<%@ page import="java.util.ResourceBundle"  %>

<jsp:useBean id="ui" class="calendar.web.bean.UIBean" scope="session" />
<jsp:setProperty name="ui" property="lang" value="<%=request.getParameter(\"lang\") %>" />
<html>
<head>
<title>CALENDAR</title>
<meta content="" name="keywords">
<meta content="" name="description">
<meta content="0" http-equiv="expires">
<meta content="no-cache" http-equiv="pragma">
<meta content="text/html; charset=UTF-8" http-equiv="Content-Type">
</head>
<body>
<%
ResourceBundle bundle = ui.getResourceBundle();
%>
<%=bundle.getString("applicationMenuAbout") %>
</body>
</html>