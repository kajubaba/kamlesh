function showEnquiryListing(academicYearId){
	window.location = enqListUrl+"/all?academicYearId="+academicYearId;
}

function validateForm(action){
	var selectedClass=$("#drpDwnClass").val();
	var firstName=jQuery.trim($("#studentFirstName").val());
	var lastName=jQuery.trim($("#studentLastName").val());
	var studentPhone1=jQuery.trim($("#studentPhone1").val());
	var studentGender=jQuery.trim($("input:radio[name=studentGender]:checked").val());
	if(  firstName !="" && lastName!="" && studentPhone1!="" && studentGender!=""){
		if("add"==action){
			saveEnquiry();	
		}else if("update"==action){
			updateEnquiry();
		}
	}else{
		alert("Mandatory Fields Are Missing !!!");	
	} 
}

function saveEnquiry(){
	var enqData = $("#enquiryForm").serialize();
	$.ajax({
		type : 'POST',
		dataType: 'json',
		url : enqUrl+"/add",
		data : enqData,
		success : saveEnquirySuccess
	});	
}

function saveEnquirySuccess(response){
	if("OK"==response.status){
		clearForm();
	}
}

function updateEnquiry(){
	var enqData = $("#enquiryForm").serialize();
	$.ajax({
		type : 'POST',
		dataType: 'json',
		url : enqUrl+"/update",
		data : enqData,
		success : updateEnquirySuccess
	});
}

function updateEnquirySuccess(response){
	if("OK"==response.status){
	}
}

function clearForm(){
	$('#enquiryForm').find(':input').each(function(){
		var type = this.type, tag = this.tagName.toLowerCase();
		if (type == 'text' || type == 'password' || tag == 'textarea')
			this.value = '';
		/*else if (type == 'checkbox' || type == 'radio')
			this.checked = false;
		else if (tag == 'select')
			this.selectedIndex = -1;
		*/
	});
}

function checkAvailability(){
	
	var firstName = jQuery.trim($("#studentFirstName").val());
	var phone = jQuery.trim($("#studentPhone1").val());
	var data="studentFirstName="+firstName+"&phone1="+phone;
	
	if(firstName!="" && phone!=""){
		$.ajax({
			type: 'GET',
			dataType: 'json',
			url : enqUrl+"/isExist",
			data : data,
			success: checkAvailabilitySuccess
		});
	}
}

function checkAvailabilitySuccess(response){
	
	if("OK"==response.status){
		$("#availabilitystatus").html("You can add this enquiry !!");
	}else if("DUPLICATE"==response.status){
		$("#availabilitystatus").html("Warning !!! Name and Phone number already exists");
	}
}

