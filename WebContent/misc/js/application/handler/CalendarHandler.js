$(document).ready(function(e) {
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
	    
		
	    //alert($(this).attr('class'));
	    //alert($(".fc-day-number").val());
	    alert($(this).children('div:first').text());
	    
        showDialog('../eventdialog.jsp', 'monDialog', buttonOpts);
        
    });
});