
samsApp.service('classSubjectService', function(ajaxService) {
	var resourceURL = _appContextPath+"/ws/academics/managesubjects";
	
	this.saveSubject = function(data, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/save";
		return ajaxService.post(data, url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getClasswiseSubjectCount = function(academicYear, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL;
		
		if(academicYear != null){
			url = resourceURL+"/classwise/subjectcount?academicYearId="+academicYear;
		}else{
			url = resourceURL+"/classwise/subjectcount";
		}
		
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	
	this.getClassWiseSubjectDetailsService = function(academicYear, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL;
		
		if(academicYear != null){
			url = resourceURL+"/classwise/SubjectDetails?academicYearClassId="+academicYear;
		}
		
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
})










