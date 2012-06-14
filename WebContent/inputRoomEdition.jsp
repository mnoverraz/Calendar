<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html>
  <head>
    <title>Room Management - Edit room</title>
  </head>
  <body>
    <h1>Edit room</h1>
    <html:form action="/inputRoomUpdate.do" method="POST">
      <html:hidden property="id" />
      <table>
        <tr>
          <td>
            Local:
          </td>
          <td>
            <html:text property="local" errorStyleClass="error" errorKey="org.apache.struts.action.ERROR" />
          </td>
          <td>
            <html:errors property="local" />
          </td>
        </tr>
        <tr>
          <td>
            Name:
          </td>
          <td>
            <html:text property="name" errorStyleClass="error" errorKey="org.apache.struts.action.ERROR" />
          </td>
          <td>
            <html:errors property="name" />
          </td>
        </tr>
        <tr>
          <td>
            Description:
          </td>
          <td>
            <html:textarea property="description" errorStyleClass="error" errorKey="org.apache.struts.action.ERROR" />
          </td>
          <td>
            <html:errors property="description" />
          </td>
        </tr>
        <tr>
          <td>
            Category:
          </td>
          <td>
          	<html:select property="roomCategory" errorStyleClass="error" errorKey="org.apache.struts.action.ERROR" >
          	  <html:option value="0">Select a room category</html:option>
			  <html:optionsCollection name="inputRoomForm" property="roomCategoryList" value="roomCategoryId" label="roomCategoryName" />
          	</html:select>
          </td>
          <td>
            <html:errors property="roomCategory" />
          </td>
        </tr>
        <tr>
          <td></td>
          <td>
            <html:submit value="Validate"/>
          </td>
        </tr>
      </table>
    </html:form>
  </body>
</html>