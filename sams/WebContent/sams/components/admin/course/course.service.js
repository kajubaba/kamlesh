
samsApp.service('courseService', function(ajaxService) {

	var resourceURL = _appContextPath+"/ws/course";
	
	
	this.getCourses = function(successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/list";
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getCourse = function(courseId, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/"+courseId;
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.deleteCourse = function(courseId, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/delete/"+courseId;
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.saveCourse = function(formData, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/save";
		return ajaxService.post(formData, url, successCallBackFunction, errorCallBackFunction);
	}
	
	
})










