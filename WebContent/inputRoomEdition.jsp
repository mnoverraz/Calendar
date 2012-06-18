<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html>
<head>
<title>UNIL - FBM CALENDAR</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<link rel="icon" href="https://wwwfbm.unil.ch/favicon.ico" type="image/x-icon" />
<link rel="shortcut icon" href="https://wwwfbm.unil.ch/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" type="text/css" href="misc/css/roomEditor.css" media="screen" />
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