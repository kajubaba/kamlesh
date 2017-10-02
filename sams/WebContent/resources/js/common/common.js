
function toggleChecked(status) {
	
	$(".checkbox").each( function() {
		$(this).attr("checked",status);
	});
}

function findCourses(){
	$("#drpDwnCourse").empty();
	$("#drpDwnClass").empty();
	
	$("#drpDwnCourse").append($("<option />").val("").text(""));
	$("#drpDwnClass").append($("<option />").val("").text(""));
	
	var auId = $("#drpDwnAffiliationAuth").val();
	var academicYearId = $("#academicYearId").val();
	
	$.ajax({
		type : 'GET',
		dataType: 'json',
		url : course_url+"/list",
		data : "affiliationAuthorityId="+auId+"&academicYearId="+academicYearId,
		success : findCoursesSuccess
	});
}

function findCoursesSuccess(courseNameList){
	var options = $("#drpDwnCourse");
	
		jQuery.each(courseNameList.courses, function(i, course) {
			if(null != course){
				options.append($("<option />").val(course.id).text(course.displayName));	
			}
			
		});
}

function findActiveClasses(){
	$("#drpDwnClass").empty();
	//$("#drpDwnClass").append($("<option />").val("").text(""));
	var academicYearId = $("#academicYearId").val();
	$.ajax({
		type : 'GET',
		dataType: 'json',
		url : course_url+"/classList",
		data : "courseId="+$("#drpDwnCourse").val()+"&academicYearId="+academicYearId,
		success : findActiveClassesSuccess
	});
}

function findActiveClassesSuccess(classNameList){
	var options = $("#drpDwnClass");
		jQuery.each(classNameList.classes, function(i, clazz) {
			if(null!=clazz){
				options.append($("<option />").val(clazz.id).text(clazz.name));	
			}
			
		});
}

function findActivePromotionClasses(){
	$("#drpDwnClass").empty();
	$("#drpDwnClass").append($("<option />").val("").text(""));
	var academicYearId = $("#academicYearId").val();
	$.ajax({
		type : 'GET',
		dataType: 'json',
		url : course_url+"/promotionClassList",
		data : "courseId="+$("#drpDwnCourse").val()+"&academicYearId="+academicYearId,
		success : findActiveClassesSuccess
	});
}

function switchInstitute(){
	
	var instituteId = $("#instituteDropDown").val();
	
	$.ajax({
		type : 'GET',
		dataType: 'json',
		url : _appContextPath+"/ws/institute/switchinstitute/"+instituteId,
		
		success : instituteSuccessSuccess
	});
}

function instituteSuccessSuccess(){
	window.location = _appContextPath+"/home";
}