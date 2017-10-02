
samsApp.service('academicSessionBusStopService', function(ajaxService) {

	var resourceURL = _appContextPath+"/ws/academic-session/bus-stop";
	
	
	this.getAcademicSessionBusStops = function(academicSessionId, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/list/"+academicSessionId;
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	
	
	this.removeBusStop = function(busFeeId, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/delete/"+busFeeId;
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.saveAcademicSessionBusStopFee = function(formData, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/save-bus-fee";
		return ajaxService.post(formData, url, successCallBackFunction, errorCallBackFunction);
	}
	
	
	
	this.getUnAssignedBusStops = function(academicSessionId, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/list/unassigned/"+academicSessionId;
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.assignBusStops = function(formData, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/assign";
		return ajaxService.post(formData, url, successCallBackFunction, errorCallBackFunction);
	}
})










