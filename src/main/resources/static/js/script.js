function showWait() {
	$("#loading").html('Processing.  Please wait ...  <img src="images/loading.gif" alt="Processing.  Please wait ..." width="13%">');
	$("#loading").
		dialog(
			{
				modal: true,
				width: 300,
				height: 100,
				show: {
					effect: "slide",
					duration: 500
				},
				hide: {
					effect: "fade",
					duration: 500
				}
			}
		);
	$(".ui-dialog-titlebar").hide();
}

function hideWait() {
	$("#loading").dialog("close");
}


function handleClick() {
	// function will get executed 
	// on click of submit button
	$("#submitChatGPT").click(function(ev) {
		$('#submitChatGPT').attr('disabled', 'disabled');
		//$("#loading").html('<div class="col-3 alert alert-warning rounded text-center">Processing.  Please wait ...  <img src="images/loading.gif" alt="Processing.  Please wait ..." width="10%"></div>');
		showWait();
		var chatGPTForm = $("#chatGPTForm");
		var url = chatGPTForm.attr('action');
		$.ajax({
			type: "POST",
			url: url,
			data: chatGPTForm.serialize(),
			success: function(result) {
				// Ajax call completed successfully                    
				$("#chatGPTConversation").html('<code class="text-justify d-flex flex-lg-wrap chat"><strong class="small">' + result + '</strong></code>');
				//alert("Form Submited Successfully");
			},
			error: function(error) {
				// Some error in ajax call
				$("#chatGPTConversation").html(error);
			},
			complete: function() {
				hideWait();
				chatGPTForm[0].reset();
				$('#submitChatGPT').removeAttr('disabled');
			}
		});

		return false;
	});
}
