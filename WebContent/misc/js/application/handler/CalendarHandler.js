$(document).ready(function() {
	$('.fc-widget-content').click(function() {
	    buttonOpts = {};
	    buttonOpts['Fermer'] = $.extend(function() {
	        $(this).dialog("close");
	    },{
	        id : 'monId'
	    });
	    buttonOpts['Button 2'] = $.extend(function() {                    
	    	//action à faire
	    }, {
	        id : 'delete'
	    });   
        showDialog('../eventdialog.jsp', 'monDialog', buttonOpts);
    });
});