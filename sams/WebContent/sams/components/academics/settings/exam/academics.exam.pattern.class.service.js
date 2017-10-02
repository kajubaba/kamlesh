
samsApp.service('examPatternClassService', function(ajaxService) {
	var resourceURL = _appContextPath+"/ws/academics/exam/pattern/class";
	
	this.getNotAddedClasses = function(examPatternId, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/notadded/"+examPatternId;
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getAddedClasses = function(examPatternId, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/list/"+examPatternId;
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.deleteClass = function(classId, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/delete/"+classId;
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.importClasses = function(classes, examPatternId, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/import/"+examPatternId;
		return ajaxService.post(classes, url, successCallBackFunction, errorCallBackFunction);
	}
	
	
})










