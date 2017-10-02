var courseAction=null;

function getCourseListView(){
	$.ajax({
		type: 'GET',
		url : admin_main_url+"/course/defaultList",
		success: getCourseListViewSuccess
	});
}

function getCourseListViewSuccess(data){
	$("#adminContainer").html(data);
	bindCourseListingWithDataTable();
}

function getNewCourseView(){
	$.ajax({
		type: 'GET',
		url : admin_main_url+"/course/new",
		success: getNewCourseViewSuccess
	});
}

function getNewCourseViewSuccess(data){
	$("#adminContainer").html(data);
	courseAction=ACTION_ADD;
	$(".numeric").numeric(false,false);
}

function saveCourse(){
	
	if(validateCourseForm()){
		if(ACTION_ADD==courseAction){
			addCourse();
		}else if(ACTION_UPDATE==courseAction){
			updateCourse();
		}	
	}
	
}

function addCourse(){
	
	var courseData = $("#courseForm").serialize();
	$.ajax({
		type : 'POST',
		dataType: 'json',
		url : admin_main_url+"/course/add",
		data : courseData,
		success : addCourseSuccess
	});	
}

function updateCourse(){
	
	var courseData = $("#courseForm").serialize();
	$.ajax({
		type : 'POST',
		dataType: 'json',
		url : admin_main_url+"/course/update",
		data : courseData,
		success : updateCourseSuccess
	});	
}

function updateCourseSuccess(response){
	if("OK"==response.status){
		//alert("Updated Successfully..");	
	}else if("DUPLICATE"==response.status){
		alert("Course name already exists. Please choose different name");
	}
}

function addCourseSuccess(response){
	
	if("OK"==response.status){
		courseAction=ACTION_UPDATE;
		$("#courseSave").val("Update");
		$("#courseId").val(response.id);
		//alert("Saved Successfully..");
	}else if("DUPLICATE"==response.status){
		alert("Course name already exists. Please choose different name");
	}
	
}

function validateCourseForm(){
	var name=jQuery.trim($("#name").val());
	var displayName=jQuery.trim($("#displayName").val());
	var affiliatedTo=jQuery.trim($("#affiliatedTo").val());
	var duration=jQuery.trim($("#duration").val());
	
	if(name!="" && displayName!="" && affiliatedTo!="" && duration!=""){
		return true;
	}else{
		alert("Mandatory Fields are missing !!");
		return false;
	}
}


function loadCourseToUpdate(courseId){
	$.ajax({
		type: 'GET',
		url : admin_main_url+"/course/get/"+courseId,
		success: loadCourseToUpdateSuccess
	});
}

function loadCourseToUpdateSuccess(data){
	$("#adminContainer").html(data);
	courseAction=ACTION_UPDATE;
	$(".numeric").numeric(false,false);
}

function changeLabel(){
	$("#ccl").html("Create "+$("#duration").val()+" course year(s) automatically");
}

function bindCourseListingWithDataTable(){
	$('#course_listing_table').dataTable({
	        "bPaginate": false,
	        "bLengthChange": false,
	        "bFilter": false,
	        "bInfo": false,
	        "bAutoWidth": false
	    } );
}