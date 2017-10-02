
samsApp.service('studentActivityService', function(ajaxService) {

	
	
	this.changeBusStop = function(formData, successCallBackFunction, errorCallBackFunction){
		var resourceURL = _appContextPath+"/ws/student/activity/changebusstop";
		return ajaxService.post(formData, resourceURL, successCallBackFunction, errorCallBackFunction);
	}
	
	this.changeClass = function(formData, successCallBackFunction, errorCallBackFunction){
		var resourceURL = _appContextPath+"/ws/student/activity/changeclass";
		return ajaxService.post(formData, resourceURL, successCallBackFunction, errorCallBackFunction);
	}
	
	this.changeAdmissionScheme = function(data, successCallBackFunction, errorCallBackFunction){
		var resourceURL = _appContextPath+"/ws/student/activity/changeadmissionscheme";
		return ajaxService.post(data, resourceURL, successCallBackFunction, errorCallBackFunction);
	}
	
	this.changeStudnetStatus = function(data, successCallBackFunction, errorCallBackFunction){
		var resourceURL = _appContextPath+"/ws/student/activity/changestudentstatus";
		return ajaxService.post(data, resourceURL, successCallBackFunction, errorCallBackFunction);
	}
	
	
	
})










