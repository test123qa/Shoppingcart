
$(document).ready(function() {
	$('<div/>', {
	    id: 'chat-window'
	}).appendTo('body');
	
	
	$('<div/>', {
	    id: 'chat-modal'
	}).appendTo('#chat-window');
	
	$('#chat-window').append('<button id="btnChatWindow">Chat with us</button>');
	
	
	$("#btnChatWindow").on("click", function() {
		$("#chat-modal").load("chatWindow.html");
		setTimeout(function(){
			$(".ui-dialog").css("position","fixed");
		})
		
	});
	
	setTimeout(function(){
		$("#btnChatWindow").click();
	},15000)
	

});