

samsApp.service('studentTransportationDetailService', function(ajaxService) {
	
	var resourceURL = _appContextPath+"/ws/transportation/student"; 
	
	this.getStduentDetails = function(studentId, successCallBackFunction, errorCallBackFunction){
		return ajaxService.get(resourceURL+"/"+studentId, successCallBackFunction, errorCallBackFunction);
	}
	
	this.updateStduentPickupPoint = function(studentId, newPointId, successCallBackFunction, errorCallBackFunction){
		if(null == newPointId || "null" == newPointId){
			newPointId ="";
		}
		return ajaxService.get(resourceURL+"/update/pickuppoint/"+studentId+"?pointId="+newPointId, successCallBackFunction, errorCallBackFunction);
	}
	
	this.updateStduentDropPoint = function(studentId, newPointId, successCallBackFunction, errorCallBackFunction){
		if(null == newPointId || "null" == newPointId){
			newPointId ="";
		}
		return ajaxService.get(resourceURL+"/update/droppoint/"+studentId+"?pointId="+newPointId, successCallBackFunction, errorCallBackFunction);
	}
	
	
});






