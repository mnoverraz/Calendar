<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:useBean id="ui" class="calendar.web.bean.UIBean" scope="session" />

<script type='text/javascript' src='misc/js/application/handler/EventDialog.js'></script>

<form id="eventform" name="eventform">
    <div class="input text">
        <label for="name" id="label-title"><%=ui.getLangText("calendar-event-title") %></label>
        <input type="text" name="title" id="title" class="required" />
        <input type="hidden" name="id" id="id" value="" />
        <!-- needs to be set to true otherwise single events will not be correctly deleted (orphelin) -->
    </div>
    <div class="input text">
        <label for="date" id="label-date"><%=ui.getLangText("calendar-event-date") %></label>
        <input type="text" name="date" id="date" class="datepicker" value=" " required="required" />
    </div>
    <div class="input">
        <label for="allDay" id="label-allDay"><%=ui.getLangText("calendar-event-wholeDay") %></label>
        <input type="checkbox" name="allDay" id="allDay" />
    </div>
    <div class="input time" id="start">

        <label for="startH" id="label-startH"><%=ui.getLangText("calendar-event-start") %></label>

        <select name="startH" id="startH">
            <%
            for (int i = 0; i <= 23; i++) {
            	String val = "";
                if (i < 10) {
                    val += "0";
                }
                val += String.valueOf(i);
                out.print("<option value=\""+ val +"\">");

                
                out.print(val + "</option>\n");
            }
            %>
        </select>
        <span>:</span>
        <select name="startM" id="startM">
            <%
            for (int i = 0; i <= 60; i+=5) {
            	String val = "";
                if (i < 10) {
                    val += "0";
                }
                val += String.valueOf(i);
                out.print("<option value=\""+ val +"\">");
                
                out.print(val + "</option>\n");
            }
            %>
        </select>
    </div>

    <div class="input time" id="end">
        <label for="endH" id="label-endH"><%=ui.getLangText("calendar-event-end") %></label>
        <select name="endH" id="endH">
            <%
            for (int i = 0; i <= 23; i++) {
            	String val = "";
                if (i < 10) {
                    val += "0";
                }
                val += String.valueOf(i);
                out.print("<option value=\""+ val +"\">");

                
                out.print(val + "</option>\n");
            }
            %>
        </select>
        <span>:</span>
        <select name="endM" id="endM">
            <%
            for (int i = 0; i <= 60; i+=5) {
            	String val = "";
                if (i < 10) {
                    val += "0";
                }
                val += String.valueOf(i);
                out.print("<option value=\""+ val +"\">");
                
                out.print(val + "</option>\n");
            }
            %>
        </select>
    </div>

    <div class="input select">
        <label for="repeatMode" id="label-repeatMode"><%=ui.getLangText("calendar-event-repeat") %></label>
        <select name="repeatMode" id="repeatMode">
            <option selected="selected" value="n" id="repeat-n"><%=ui.getLangText("calendar-event-never") %></option>
            <option value="d" id="repeat-d"><%=ui.getLangText("calendar-event-daily") %></option>
            <option value="w" id="repeat-w"><%=ui.getLangText("calendar-event-weekly") %></option>
            <option value="2w" id="repeat-2w"><%=ui.getLangText("calendar-event-halfMonthly") %></option>
            <option value="m" id="repeat-m"><%=ui.getLangText("calendar-event-monthly") %></option>
            <option value="y" id="repeat-y"><%=ui.getLangText("calendar-event-yearly") %></option>
        </select>
    </div>
    <div class="input text" id="repeat_date">
        <label for="repeatEnd" id="label-repeatEnd"><%=ui.getLangText("calendar-event-until") %></label>
        <input type="text" name="repeatEnd" id="repeatEnd" class="datepicker" value=" " />
    </div>

    <div class="input textarea">
        <label for="description" id="label-description"><%=ui.getLangText("calendar-event-description") %></label>
        <textarea name="description" id="description" cols="20" rows="20"></textarea>
    </div>
</form>

<!-- END NEW EVENT DIALOG -->
