
samsApp.service('studentListService', function(ajaxService) {

	this.getStudents = function(admissionType, academicYearId, successCallBackFunction, errorCallBackFunction){
		var resourceURL = _appContextPath+"/ws/admissions/studentView/"+admissionType;
		
		if(academicYearId != null && academicYearId != "null"){
			resourceURL = resourceURL+"?ayid="+academicYearId;
		}
		
		return ajaxService.get(resourceURL, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getStudentsOfClass = function(classId, studentStatusId, successCallBackFunction, errorCallBackFunction){
		var resourceURL = _appContextPath+"/ws/admissions/classwise/"+classId+"/"+studentStatusId;
		return ajaxService.get(resourceURL, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getNewStudents = function(successCallBackFunction, errorCallBackFunction){
		 
		var resourceURL = _appContextPath+"/ws/admissions/studentView/new";
		return ajaxService.get(resourceURL, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getClasswiseAdmissions = function(admissionType, academicYearId, successCallBackFunction, errorCallBackFunction){
		
		var resourceURL = _appContextPath+"/ws/admissions/classview/"+admissionType;
		if(academicYearId != null && academicYearId != "null"){
			resourceURL = resourceURL+"?ayid="+academicYearId;
		}
		
		
		return ajaxService.get(resourceURL, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getActiveClasses = function(successCallBackFunction, errorCallBackFunction){
		var resourceURL = _appContextPath+"/ws/admissions/activeclasses";
		return ajaxService.get(resourceURL, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getUnderSchemeAdmissions = function(studentStatus, academicYearId, successCallBackFunction, errorCallBackFunction){
		var resourceURL = _appContextPath+"/ws/admissions/underScheme/"+studentStatus;
		if(academicYearId != null && academicYearId != "null"){
			resourceURL = resourceURL+"?ayid="+academicYearId;
		}
		return ajaxService.get(resourceURL, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getUnderSchemeNewAdmissions = function(successCallBackFunction, errorCallBackFunction){
		var resourceURL = _appContextPath+"/ws/admissions/underScheme/new";
		return ajaxService.get(resourceURL, successCallBackFunction, errorCallBackFunction);
	}
	
})










