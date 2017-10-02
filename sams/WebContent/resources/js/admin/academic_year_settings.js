function aySettingDefaultPage(academicYearId){
	$.ajax({
		type: 'GET',
		url : admin_ay_settings_url+"/default?academicYearId="+academicYearId,
		success: aySettingDefaultPageSuccess
	});
}

function aySettingDefaultPageSuccess(data){
	selected_fee_link="";
	$("#adminContainer").html(data);
}

function ayGetActiveCourses(){
	
	var searchAYCourseFormData = $("#searchAYCourseForm").serialize();
	
	$.ajax({
		type: 'GET',
		url : admin_ay_settings_url+"/courses",
		data : searchAYCourseFormData,
		success: ayGetActiveCoursesSuccess
	});
}

function ayGetActiveCoursesSuccess(data){
	$("#ayCourseList").html(data);
}

function toggleClassess(index){
	$("#courseClasses-"+index).slideToggle("slow");
}

function closeFeePopup(){
	$('#popupFee, #popupBackground,#popupRemainingCourses, #popupRemainingBusStops, #popupFeeInstallment, #popupBusFeeInstallment, #popupRemainingHostels, #popupHostelFeeInstallment,#popupCourseYearProperties').hide();
}

function removeCourse(courseId, divId){
	$.ajax({
		type: 'DELETE',
		dataType: 'json',
		url : admin_ay_settings_url+"/delete/course/"+courseId,
		success: function(json) {
			removeCourseSuccess(json, divId);
		     }
	});
}

function removeCourseSuccess(response,divId){
	$("#"+divId).remove();
	
}

function getReminingCourseListPopup(academicYearId,affiliationAuthorityId){
	
	var searchAYCourseFormData = $("#searchAYCourseForm").serialize();
	
	$.ajax({
		type: 'GET',
		url : admin_ay_settings_url+"/course/notadded",
		data : searchAYCourseFormData,
		success: getReminingCourseListPopupSuccess
	});
}

function getReminingCourseListPopupSuccess(data){
	$("#remainingCoursesContainer").html(data);
	$('#popupRemainingCourses, #popupBackground').show();
}

function addCoursesInAcademicYear(){
	var reminingCourseFormData = $("#reminingCourseForm").serialize();
	
	$.ajax({
		type: 'POST',
		url : admin_ay_settings_url+"/course/assign",
		data : reminingCourseFormData,
		success: addCoursesInAcademicYearSuccess
	});
}

function addCoursesInAcademicYearSuccess(response){
	$("#ayCourseList").html(response);
	closeFeePopup();
}

function viewCourseYearSettingProp(courseYearSettingId){
	$.ajax({
		type: 'GET',
		url : admin_ay_settings_url+"/course/managePropView?courseYearSettingId="+courseYearSettingId,
		success: viewCourseYearSettingPropSuccess
	});
}

function viewCourseYearSettingPropSuccess(response){
	$("#popupCourseYearProperties").html(response);
	$("#popupBackground").show();
	$("#popupCourseYearProperties").show();
}

function saveCourseYearSettingProp(courseYearSettingId){
	var courseYearSettingFormData = $("#courseYearSettingForm").serialize();
	$.ajax({
		type: 'POST',
		dataType: 'json',
		url : admin_ay_settings_url+"/course/saveProp",
		data : courseYearSettingFormData,
		success: saveCourseYearSettingPropSuccess
	});
}

function saveCourseYearSettingPropSuccess(response){
	if(response.status=="OK"){
		ayGetActiveCourses();
		closeFeePopup();
	}
}