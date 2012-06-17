<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<jsp:useBean id="ui" class="calendar.ui.bean.UIBean" scope="session" />
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
<link rel="stylesheet" type="text/css" href="misc/css/main.css" media="screen" />
<link rel="stylesheet" type="text/css" href="misc/css/fullcalendar.css" media="screen" />
<link rel="stylesheet" type="text/css" href="misc/css/fullcalendar.print.css" media="print" />
<link rel="stylesheet" type="text/css" href="misc/css/calendar.css" media="screen" />
<link rel="stylesheet" type="text/css" href="misc/css/jquery-ui-1.8.4.custom.css" media="screen" />

<!-- BEGIN JQUERY -->
<script type='text/javascript' src='misc/js/jquery/jquery-1.7.1.min.js'></script>
<script type='text/javascript' src='misc/js/jquery/jquery.ui.core.js'></script>
<script type='text/javascript' src='misc/js/jquery/jquery.plugin.js'></script>
<script type='text/javascript' src='misc/js/fullcalendar/fullcalendar.js'></script>
<!-- END JQUERY -->

<!-- BEGIN APPLICATION -->

<script type='text/javascript' src='misc/js/lang.jsp'></script>
<script type='text/javascript' src='misc/js/application/helpers/dateHelper.js'></script>
<script type='text/javascript' src='misc/js/application/init.js'></script>
<script type='text/javascript' src='misc/js/application/controller/Controller.js'></script>
<!--script type='text/javascript' src='misc/js/application/handler/CalendarHandler.js'></script-->
<!-- END APPLICATION -->
</head>
<body>

<!-- BEGIN PAGE -->
	<div id="page">
		<!-- BEBIN HEADER -->
		<div id="header">
			<!--img id="login" src="https://wwwfbm.unil.ch/html/img/login.gif"
				alt="login" /-->
			<div id="language"><a href="config?lang=fr" class="language">Français</a> | <a href="config?lang=en" class="language">English</a> | <a href="config?lang=ja"  class="language">日本語</a></div>
			<div id="bar"><script type='text/javascript'>document.write(resourceBundle['application-title']);</script></div>
		</div>
		
		<!-- END HEADER -->
		<!-- BEGIN CONTENT -->
		<div id="content">
			<!-- BEGIN MAIN -->
			<div id="main">
				<!-- BEGIN CALENDAR -->
				<div id="toolbar"></div>
				<div id="calendar"></div>
				<!-- END CALENDAR -->
			</div>
			<!-- END MAIN -->
			<!-- BEGIN SIDEBAR -->
			<div id="sidebar">
				<!-- BEGIN ROOM-VIEW -->
				<div id="room">
					<!-- BEGIN INFORMATION -->
					<div class="box">
						<h1>Information <span id="room_category"></span> <span id="room_local"></span></h1>
						<div>
							<span id="room_description"></span>
						</div>
					</div>
					<!-- END INFORMATION -->
					<!-- BEGIN CONTACT -->
					<div class="box">
						<h1>Admin Room</h1>
						<div>
							<a href="roomManagement.do">Administration</a>
						</div>
					</div>
					<!-- END CONTACT -->
				</div>
				<!-- END ROOM-VIEW -->
				<!-- BEGIN CONTROL-VIEW -->
				<div id="control"></div>
				<!-- END CONTROL-VIEW -->
			</div>
			<!-- END SIDEBAR -->
		</div>
		<!-- END CONTENT -->
		
		<!-- BEGIN FOOTER -->
		<div id="footer">&copy; 2012 - Université de Lausanne - All
			right reserved</div>
		<!-- END FOOTER -->
	</div>
	<!-- END PAGE -->
</body>
</html>