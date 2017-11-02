
$(document).ready(function() {
	$('<div/>', {
	    id: 'chat-window'
	}).appendTo('body');
	$('<div/>', {
	    id: 'chat-modal'
	}).appendTo('#chat-window');
	$('#chat-window').append('<button id="btnChatWindow">Chat</button>');
	$("#btnChatWindow").on("click", function() {
		$("#chat-modal").load("app/views/chatWindow.html");
	});
});