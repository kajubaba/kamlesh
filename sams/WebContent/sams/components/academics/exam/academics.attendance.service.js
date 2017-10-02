
samsApp.service('attendanceService', function(ajaxService) {
	var resourceURL = _appContextPath+"/ws/academics/attendance";
	
	this.getStudentsForAttendance = function(classId, sectionId, termId, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/get/students?classId="+classId+"&sectionId="+sectionId+"&termId="+termId;
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.saveAttendance = function(data, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/save";
		return ajaxService.post(data, url, successCallBackFunction, errorCallBackFunction);
	}
	
})










