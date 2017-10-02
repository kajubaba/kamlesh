
samsApp.service('busstopService', function(ajaxService) {

	var resourceURL = _appContextPath+"/ws/bus-stop";
	
	
	this.getBusStops = function(successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/list";
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getBusStop = function(busStopId, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/"+busStopId;
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.deleteBusStop = function(busStopId, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/delete/"+busStopId;
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.saveBusStop = function(formData, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/save";
		return ajaxService.post(formData, url, successCallBackFunction, errorCallBackFunction);
	}
	
	
})










