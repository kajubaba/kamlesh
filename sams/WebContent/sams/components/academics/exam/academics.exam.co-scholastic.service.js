
samsApp.service('coScholasticExamService', function(ajaxService) {
	var resourceURL = _appContextPath+"/ws/academics/scorecollection/co-scholastic/score";
	
	this.getStudents = function(classId, sectionId, termId, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/getStudents?classId="+classId+"&termId="+termId+"&sectionId="+sectionId;
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getStudentCoScholasticScore = function(studentId, classId, termId, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/get?studentId="+studentId+"&classId="+classId+"&termId="+termId;
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	
	this.saveStudentCoscholasticScore = function(data, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/save";
		return ajaxService.post(data, url, successCallBackFunction, errorCallBackFunction);
	}
})










