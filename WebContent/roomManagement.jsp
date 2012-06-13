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
    <input type="button" value="Add room" onclick="self.location.href='inputRoom.do'" />
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
        	<bean:define id="idValue" name="room" property="id" type="Long" />  
		  <%
		  	String id = String.valueOf(idValue);
		  	out.print("<input type=\"button\" value=\"Edit room\" onclick=\"self.location.href=''\" />");
		  	out.print("<input type=\"button\" value=\"Delete room\" onclick=\"self.location.href='inputRoomRemove.do?id=" + id + "'\" />");
		  %>
        </td>      
      </tr>
    </logic:iterate>
    </table>
  </body>
</html>

