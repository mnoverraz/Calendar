<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<script type='text/javascript' src='misc/js/application/eventdialog.js'></script>

<form id="eventform" name="eventform" action="" method="post">


<?php if ($mode == "edit") { ?>
	<div class="input text">


	<?php if ($uid != $currentUser) {
		?>
		<label for="creator" id="event-user"><?php echo $languageLinker->resourceBundle->get("calendar-event-owner");?>
		</label> <input type="text" name="creator" id="creator"
			value="<?php echo $uid ?>" readonly="readonly" />
		
		
		
		
            <?php } ?>
            <!-- original entries, to know which part has been modified -->
            <input type="hidden" name="original_name" id="original_name" value="<?php echo $title; ?>"/>
            <input type="hidden" name="original_date" id="original_date" value="<?php echo $edate ?>" />
            <input type="hidden" name="original_whole_day" id="original_whole_day" value="<?php echo $wholeDay; ?>"/>
            <input type="hidden" name="original_start_time" id="original_start_hour" value="<?php echo "$startH:$startM:00"; ?>"/>
            <input type="hidden" name="original_end_time" id="original_end_hour" value="<?php echo "$endH:$endM:00"; ?>"/>
            <input type="hidden" name="original_repeat_mode" id="original_repeat_mode" value="<?php echo $repeatMode; ?>"/>
            <input type="hidden" name="original_repeat_end" id="original_repeat_end" value="<?php echo $repeatEnd; ?>"/>
            <input type="hidden" name="original_description" id="original_description" value="<?php echo $description; ?>"/>
        </div>
	
	
	
	
    <?php } ?>
    <div class="input text">
        <label for="name" id="event-title"><?php echo $languageLinker->resourceBundle->get("calendar-event-title");?></label>
        <input type="text" name="name" id="name" class="required" value="<?php echo $title; ?>"/>
        <input type="hidden" name="uid" id="uid" value="<?php echo $currentUser ?>"/>
        <input type="hidden" name="action" id="action" value="<?php echo $mode ?>" />
        <input type="hidden" name="event_id" id="event_id" value="<?php echo $event_id ?>" />
        <input type="hidden" name="date_id" id="date_id" value="<?php echo $date_id ?>" />
        <!-- needs to be set to true otherwise single events will not be correctly deleted (orphelin) -->
        <input type="hidden" name="modifyall" id="modifyall" value="true" />
    </div>
    <div class="input text">
        <label for="edate" id="event-date"><?php echo $languageLinker->resourceBundle->get("calendar-event-date");?></label>
        <input type="text" name="edate" id="edate" class="datepicker" value="<?php echo $edate ?>" readonly/>
    </div>
    <div class="input">
        <label for="whole_day" id="event-whole-day"><?php echo $languageLinker->resourceBundle->get("calendar-event-wholeDay");?></label>
        <input type="checkbox" name="whole_day" id="whole_day" <?php echo ($wholeDay) ? "checked=\"checked\"" : ""; ?> />
    </div>
    <div class="input time" id="start">

        <label for="start_hour" id="event-start"><?php echo $languageLinker->resourceBundle->get("calendar-event-start");?></label>

        <select name="start_hour" id="start_hour">
            <?php
            for ($i = 0; $i <= 23; $i++) {
                echo "<option value=\"$i\"";
                if ($startH == $i) {
                    echo " selected";
                }
                echo ">";
                if ($i < 10) {
                    echo "0";
                }

                echo $i . "</option>\n";
            }
            ?>
        </select>
        <span>:</span>
        <select name="start_min" id="start_min">
            <?php
            for ($i = 0; $i < 60; $i += 5) {
                echo "<option value=\"$i\"";
                if ($startM == $i) {
                    echo " selected";
                }
                echo ">";
                if ($i < 10) {
                    echo "0";
                }

                echo $i . "</option>\n";
            }
            ?>
        </select>
    </div>

    <div class="input time" id="end">
        <label for="end_hour" id="event-end"><?php echo $languageLinker->resourceBundle->get("calendar-event-end");?></label>
        <select name="end_hour" id="end_hour">
            <?php
            for ($i = 0; $i <= 23; $i++) {
                echo "<option";
                if ($endH == $i) {
                    echo " selected";
                }
                echo ">";
                if ($i < 10) {
                    echo "0";
                }

                echo $i . "</option>\n";
            }
            ?>
        </select>
        <span>:</span>
        <select name="end_min" id="end_min">
            <?php
            for ($i = 0; $i < 60; $i += 5) {
                echo "<option";
                if ($endM == $i) {
                    echo " selected";
                }
                echo ">";
                if ($i < 10) {
                    echo "0";
                }

                echo $i . "</option>\n";
            }
            ?>
        </select>
    </div>

    <div class="input select">
        <label for="repeat" id="event-repeat"><?php echo $languageLinker->resourceBundle->get("calendar-event-repeat");?></label>
        <select name="repeat" id="repeat">
            <option <?php echo ($repeatMode == 'n') ? "selected=\"selected\"" : "" ?> value="n" id="repeat-n"><?php echo $languageLinker->resourceBundle->get("calendar-event-never");?></option>
            <option <?php echo ($repeatMode == 'd') ? "selected=\"selected\"" : "" ?> value="d" id="repeat-d"><?php echo $languageLinker->resourceBundle->get("calendar-event-daily");?></option>
            <option <?php echo ($repeatMode == 'w') ? "selected=\"selected\"" : "" ?> value="w" id="repeat-w"><?php echo $languageLinker->resourceBundle->get("calendar-event-weekly");?></option>
            <option <?php echo ($repeatMode == '2w') ? "selected=\"selected\"" : "" ?> value="2w" id="repeat-2w"><?php echo $languageLinker->resourceBundle->get("calendar-event-halfMonthly");?></option>
            <option <?php echo ($repeatMode == 'm') ? "selected=\"selected\"" : "" ?> value="m" id="repeat-m"><?php echo $languageLinker->resourceBundle->get("calendar-event-monthly");?></option>
            <option <?php echo ($repeatMode == 'y') ? "selected=\"selected\"" : "" ?> value="y" id="repeat-y"><?php echo $languageLinker->resourceBundle->get("calendar-event-yearly");?></option>
        </select>
    </div>
    <div class="input text" id="repeat_date">
        <label for="repeat_end" id="repeat-until"><?php echo $languageLinker->resourceBundle->get("calendar-event-until");?></label>
        <input type="text" name="repeat_end" id="repeat_end" class="datepicker" value="<?php echo $repeatEnd ?>" readonly/>
    </div>

    <div class="input textarea">
        <label for="description" id="event-description"><?php echo $languageLinker->resourceBundle->get("calendar-event-description");?></label>
        <textarea name="description" id="description" cols="20" rows="20"><?php echo $description ?></textarea>
    </div>
</form>

<!-- END NEW EVENT DIALOG -->
