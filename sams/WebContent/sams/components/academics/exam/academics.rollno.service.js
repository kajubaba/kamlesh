
samsApp.service('rollNoService', function(ajaxService) {
	var resourceURL = _appContextPath+"/ws/academics/rollno";
	
	this.getStudentsForRollNo = function(classId, sectionId, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/students?classId="+classId+"&sectionId="+sectionId;
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.saveRollNos = function(data, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/save";
		return ajaxService.post(data, url, successCallBackFunction, errorCallBackFunction);
	}
	
})










