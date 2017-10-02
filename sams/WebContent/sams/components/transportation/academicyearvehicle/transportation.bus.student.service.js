

samsApp.service('busStudentService', function(ajaxService) {
	
	var resourceURL = _appContextPath+"/ws/transportation/busstudent";
	
	this.getArrivalStudents = function(academicSessionBusId, successCallBackFunction, errorCallBackFunction){
		return ajaxService.get(resourceURL+"/arrival/list/"+academicSessionBusId, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getDepartureStudents = function(academicSessionBusId, successCallBackFunction, errorCallBackFunction){
		return ajaxService.get(resourceURL+"/departure/list/"+academicSessionBusId, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getRouteStudents = function(routeId, successCallBackFunction, errorCallBackFunction){
		return ajaxService.get(resourceURL+"/route/"+routeId, successCallBackFunction, errorCallBackFunction);
	}
	
	
});






