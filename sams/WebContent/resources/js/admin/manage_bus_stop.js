var busStopAction=null;

function getBusStopListView(){
	$.ajax({
		type: 'GET',
		url : admin_bus_stop_url+"/defaultList",
		success: getBusStopListViewSuccess
	});
}

function getBusStopListViewSuccess(response){
	$("#adminContainer").html(response);
	bindBusStopWithDataTable();
}

function getNewBusStopView(){
	$.ajax({
		type: 'GET',
		url : admin_bus_stop_url+"/new",
		success: getNewBusStopViewSuccess
	});
}

function getNewBusStopViewSuccess(response){
	$("#adminContainer").html(response);
	$(".numeric").numeric(false,false);
	busStopAction=ACTION_ADD;
}

function validateBusStopForm(){
	var name=jQuery.trim($("#name").val());
	if(name!=""){
		return true;
	}else{
		alert("Mandatory Fields are missing !!");
		return false;
	}
	
}

function saveBusStop(){
	
	if(validateBusStopForm()){
		if(ACTION_ADD==busStopAction){
			addBusStop();
		}else if(ACTION_UPDATE==busStopAction){
			updateBusStop();
		}	
	}
	
}

function addBusStop(){
	
	var busStopFormData = $("#busStopForm").serialize();
	$.ajax({
		type : 'POST',
		dataType: 'json',
		url : admin_bus_stop_url+"/add",
		data : busStopFormData,
		success : addBusStopSuccess
	});	
}

function addBusStopSuccess(response){
	
	if("OK"==response.status){
		busStopAction=ACTION_UPDATE;
		$("#busStopSave").val("Update");
		$("#busStopId").val(response.id);
		//alert("Saved Successfully..");
	}else if("DUPLICATE"==response.status){
		alert("Bus stop already exists. Please choose different name");
	}
	
}

function loadBusStopToUpdate(busStopId){
	$.ajax({
		type: 'GET',
		url : admin_bus_stop_url+"/get/"+busStopId,
		success: loadBusStopToUpdateSuccess
	});
}

function loadBusStopToUpdateSuccess(response){
	$("#adminContainer").html(response);
	$(".numeric").numeric(false,false);
	busStopAction=ACTION_UPDATE;
}

function updateBusStop(){
	
	var busStopFormData = $("#busStopForm").serialize();
	$.ajax({
		type : 'POST',
		dataType: 'json',
		url : admin_bus_stop_url+"/update",
		data : busStopFormData,
		success : updateBusStopSuccess
	});	
}

function updateBusStopSuccess(response){
	if("OK"==response.status){
		//alert("Updated Successfully..");	
	}else if("DUPLICATE"==response.status){
		alert("Bus stop already exists. Please choose different name");
	}
}


function bindBusStopWithDataTable(){
	
	$('#bus_stop_table').dataTable({
	        "bPaginate": false,
	        "bLengthChange": false,
	        "bFilter": false,
	        "bInfo": false,
	        "bAutoWidth": false,
	        "aoColumns": [
	                      null,
	                      null,
	                      null,
	                      null,
	                      {"bVisible": false},
	                      {"iDataSort": 4}
	                      
	                     ]
	    } );
	
}

