<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html>
  <head>
    <title>Room Management - Add room</title>
  </head>
  <body>
    <h1>Add room</h1>
    <html:errors/>
    <html:form action="/addRoom.do" method="POST">
      <html:hidden property="id" />
      <table>
        <tr>
          <td>
            Local:
          </td>
          <td>
            <html:text property="local" />
          </td>
        </tr>
        <tr>
          <td>
            Name:
          </td>
          <td>
            <html:text property="name" />
          </td>
        </tr>
        <tr>
          <td>
            Description:
          </td>
          <td>
            <html:textarea property="description" />
          </td>
        </tr>
        <tr>
          <td>
            Category:
          </td>
          <td>
<%--             <html:text property="roomCategory" /> --%>
            <html:select property="roomCategory" />
          </td>
        </tr>
        <tr>
          <td>
            
          </td>
          <td>
            <html:submit value="Validate"/>
          </td>
        </tr>
      </table>
    </html:form>
  </body>
</html>