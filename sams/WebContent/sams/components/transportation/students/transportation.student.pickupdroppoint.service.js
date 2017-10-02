

samsApp.service('transStudentPickupDropPointService', function(ajaxService) {
	
	this.getStudentsWithPickDropPoint = function(busStopId, successCallBackFunction, errorCallBackFunction){
		var resourceURL = _appContextPath+"/ws/transportation/studentpickupdroppoint/getBusStopWise/"+busStopId;
		return ajaxService.get(resourceURL, successCallBackFunction, errorCallBackFunction);
	}
	

	this.updateStudentPickupPoint = function(data, successCallBackFunction, errorCallBackFunction){
		var resourceURL = _appContextPath+"/ws/transportation/studentpickupdroppoint/updatepickuppoint";
		return ajaxService.post(data, resourceURL, successCallBackFunction, errorCallBackFunction);
	}
	
	this.updateStudentDropPoint = function(data, successCallBackFunction, errorCallBackFunction){
		var resourceURL = _appContextPath+"/ws/transportation/studentpickupdroppoint/updatedroppoint";
		return ajaxService.post(data, resourceURL, successCallBackFunction, errorCallBackFunction);
	}
});






