<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:useBean id="ui" class="calendar.web.bean.UIBean" scope="session" />

<script type='text/javascript' src='misc/js/application/handler/EventDialog.js'></script>


<form id="eventform" name="eventform">




    <div class="input text">
        <label for="name" id="event-title"><%=ui.getLangText("calendar-event-title") %></label>
        <input type="text" name="name" id="name" class="required" value=""/>
        <input type="hidden" name="uid" id="uid" value="uid"/>
        <input type="hidden" name="action" id="action" value="action" />
        <input type="hidden" name="event_id" id="event_id" value="event_id" />
        <input type="hidden" name="date_id" id="date_id" value="date_id" />
        <!-- needs to be set to true otherwise single events will not be correctly deleted (orphelin) -->
        <input type="hidden" name="modifyall" id="modifyall" value="true" />
    </div>
    <div class="input text">
        <label for="edate" id="event-date"><%=ui.getLangText("calendar-event-date") %></label>
        <input type="text" name="edate" id="edate" class="datepicker" value=" " required="required" />
    </div>
    <div class="input">
        <label for="whole_day" id="event-whole-day"><%=ui.getLangText("calendar-event-wholeDay") %></label>
        <input type="checkbox" name="whole_day" id="whole_day" />
    </div>
    <div class="input time" id="start">

        <label for="start_hour" id="event-start"><%=ui.getLangText("calendar-event-start") %></label>

        <select name="start_hour" id="start_hour">
            <%
            for (int i = 0; i <= 23; i++) {
                out.print("<option value=\""+ i +"\">");
                if (i < 10) {
                    out.print("0");
                }
                
                out.print(i + "</option>\n");
            }
            %>
        </select>
        <span>:</span>
        <select name="start_min" id="start_min">
            <%
            for (int i = 0; i <= 60; i+=5) {
                out.print("<option value=\""+ i +"\">");
                if (i < 10) {
                    out.print("0");
                }
                
                out.print(i + "</option>\n");
            }
            %>
        </select>
    </div>

    <div class="input time" id="end">
        <label for="end_hour" id="event-end"><%=ui.getLangText("calendar-event-end") %></label>
        <select name="end_hour" id="end_hour">
            <%
            for (int i = 0; i <= 23; i++) {
                out.print("<option value=\""+ i +"\">");
                if (i < 10) {
                    out.print("0");
                }
                
                out.print(i + "</option>\n");
            }
            %>
        </select>
        <span>:</span>
        <select name="end_min" id="end_min">
            <%
            for (int i = 0; i <= 60; i+=5) {
                out.print("<option value=\""+ i +"\">");
                if (i < 10) {
                    out.print("0");
                }
                
                out.print(i + "</option>\n");
            }
            %>
        </select>
    </div>

    <div class="input select">
        <label for="repeat" id="event-repeat"><%=ui.getLangText("calendar-event-repeat") %></label>
        <select name="repeat" id="repeat">
            <option selected="selected" value="<%=ui.getLangText("calendar-event-never") %>" id="repeat-n"></option>
            <option value="d" id="repeat-d"><%=ui.getLangText("calendar-event-daily") %></option>
            <option value="w" id="repeat-w"><%=ui.getLangText("calendar-event-weekly") %></option>
            <option value="2w" id="repeat-2w"><%=ui.getLangText("calendar-event-halfMonthly") %></option>
            <option value="m" id="repeat-m"><%=ui.getLangText("calendar-event-monthly") %></option>
            <option value="y" id="repeat-y"><%=ui.getLangText("calendar-event-yearly") %></option>
        </select>
    </div>
    <div class="input text" id="repeat_date">
        <label for="repeat_end" id="repeat-until"><%=ui.getLangText("calendar-event-until") %></label>
        <input type="text" name="repeat_end" id="repeat_end" class="datepicker" value="repeat_end" readonly="readonly"/>
    </div>

    <div class="input textarea">
        <label for="description" id="event-description"><%=ui.getLangText("calendar-event-description") %></label>
        <textarea name="description" id="description" cols="20" rows="20"></textarea>
    </div>
</form>

<!-- END NEW EVENT DIALOG -->
