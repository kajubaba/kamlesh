

samsApp.service('translationService', function(ajaxService) {

	var resourceURL = _appContextPath+"/ws/translations";
	
	this.getStudentTranslations = function(classId, studentStatusId, successCallBackFunction, errorCallBackFunction){
		return ajaxService.get(resourceURL+"/students?classId="+classId+"&stduentStatusId="+studentStatusId, successCallBackFunction, errorCallBackFunction);
	}
	
	this.updateStduentTranslations = function(formData, successCallBackFunction, errorCallBackFunction){
		return ajaxService.put(formData, resourceURL+"/students", successCallBackFunction, errorCallBackFunction);
	}
	
	this.getBusStopTranslations = function(successCallBackFunction, errorCallBackFunction){
		return ajaxService.get(resourceURL+"/busstops", successCallBackFunction, errorCallBackFunction);
	}
	
	this.updateBusStopTranslations = function(formData, successCallBackFunction, errorCallBackFunction){
		return ajaxService.put(formData, resourceURL+"/busstops", successCallBackFunction, errorCallBackFunction);
	}
	
})











