var hostelAction=null;

function getHostelListView(){
	$.ajax({
		type: 'GET',
		url : admin_hostel_url+"/list",
		success: getHostelListViewSuccess
	});
}

function getHostelListViewSuccess(response){
	$("#adminContainer").html(response);
	bindHostelWithDataTable();
}

function getNewHostelView(){
	$.ajax({
		type: 'GET',
		url : admin_hostel_url+"/new",
		success: getNewHostelViewSuccess
	});
}

function getNewHostelViewSuccess(response){
	$("#adminContainer").html(response);
	hostelAction=ACTION_ADD;
}

function validateHostelForm(){
	var name=jQuery.trim($("#name").val());
	if(name!=""){
		return true;
	}else{
		alert("Mandatory Fields are missing !!");
		return false;
	}
	
}

function saveHostel(){
	
	if(validateHostelForm()){
		if(ACTION_ADD==hostelAction){
			addHostel();
		}else if(ACTION_UPDATE==hostelAction){
			updateHostel();
		}	
	}
	
}

function addHostel(){
	
	var hostelFormData = $("#hostelForm").serialize();
	$.ajax({
		type : 'POST',
		dataType: 'json',
		url : admin_hostel_url+"/add",
		data : hostelFormData,
		success : addHostelSuccess
	});	
}

function addHostelSuccess(response){
	
	if("OK"==response.status){
		hostelAction=ACTION_UPDATE;
		$("#hostelSave").val("Update");
		$("#hostelId").val(response.id);
		//alert("Saved Successfully..");
	}else if("DUPLICATE"==response.status){
		alert("Hostel name already exists. Please choose different name");
	}
	
}

function loadHostelToUpdate(hostelId){
	$.ajax({
		type: 'GET',
		url : admin_hostel_url+"/get/"+hostelId,
		success: loadHostelToUpdateSuccess
	});
}

function loadHostelToUpdateSuccess(response){
	$("#adminContainer").html(response);
	hostelAction=ACTION_UPDATE;
}

function updateHostel(){
	
	var hostelFormData = $("#hostelForm").serialize();
	$.ajax({
		type : 'POST',
		dataType: 'json',
		url : admin_hostel_url+"/update",
		data : hostelFormData,
		success : updateHostelSuccess
	});	
}

function updateHostelSuccess(response){
	if("OK"==response.status){
		//alert("Updated Successfully..");	
	}else if("DUPLICATE"==response.status){
		alert("Hostel name already exists. Please choose different name");
	}
}


function bindHostelWithDataTable(){
	
	$('#hostel_table').dataTable({
	        "bPaginate": false,
	        "bLengthChange": false,
	        "bFilter": false,
	        "bInfo": false,
	        "bAutoWidth": false,
	        "aoColumns": [
	                      null,
	                      null,
	                      null,
	                      {"bVisible": false},
	                      {"iDataSort": 3}
	                      
	                     ]
	    } );
	
}

