<%@page import="calendar.core.entity.RoomCategory"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html>
  <head>
    <title>Room Management</title>
  </head>
  <body>
    <h1>Room Management</h1>
    <br/>
    <input type="button" value="Add room" onclick="self.location.href='addRoom.jsp'" />
    <br/>
    <hr/>
    <table border="1">
      <tr>
	    <th>Local</th>
	    <th>Name</th>
	    <th>Description</th>
	    <th>Category</th>
	    <th>Actions</th>
	  </tr>
    <logic:iterate id="room" name="rooms">
      <tr>
        <td>
          <bean:write name="room" property="local" />
        </td>
        <td>
          <bean:write name="room" property="name" />
        </td>
        <td>
          <bean:write name="room" property="description" />
        </td>
        <td>
          <bean:define id="roomCategoryValue" name="room" property="roomCategory" type="calendar.core.entity.RoomCategory"/>  
		  <%
		    RoomCategory rc = roomCategoryValue;
		    out.print(rc.getName());
		  %>
        </td>      
        <td>
      
          </td>      
      </tr>
    </logic:iterate>
    </table>
  </body>
</html>

