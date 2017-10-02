samsApp.service('studentStatusService', function(ajaxService) {

	var resourceURL = _appContextPath+"/ws/studentstatus";
	
	
	this.getStudentStatusList = function(successCallBackFunction, errorCallBackFunction){
		var url = resourceURL;
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getStudentStatus = function(studentStatusId, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/"+studentStatusId;
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.addStudentStatus = function(formData, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL;
		return ajaxService.post(formData, url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.updateStudentStatus = function(formData, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL;
		return ajaxService.put(formData, url, successCallBackFunction, errorCallBackFunction);
	}
	
	
	
})











