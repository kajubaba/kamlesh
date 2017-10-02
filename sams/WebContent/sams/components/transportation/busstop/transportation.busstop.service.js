

samsApp.service('busStopService', function(ajaxService) {
	
	this.getAllBusStops = function(successCallBackFunction, errorCallBackFunction){
		var resourceURL = _appContextPath+"/ws/transportation/busstop/allstops";
		return ajaxService.get(resourceURL, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getBusStopCount = function(successCallBackFunction, errorCallBackFunction){
		var resourceURL = _appContextPath+"/ws/transportation/busstop/count";
		return ajaxService.get(resourceURL, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getBusStopPickupDropPoints = function(busStopId, successCallBackFunction, errorCallBackFunction){
		var resourceURL = _appContextPath+"/ws/transportation/busstop/pickupdroppoints/"+busStopId;
		return ajaxService.get(resourceURL, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getBusStopPickupDropPointsByBusStop = function(busStopId, successCallBackFunction, errorCallBackFunction){
		var resourceURL = _appContextPath+"/ws/transportation/busstop/pickupdroppointsbybusstopid/"+busStopId;
		return ajaxService.get(resourceURL, successCallBackFunction, errorCallBackFunction);
	}
	
	this.addNewBusStopPoint = function(data, successCallBackFunction, errorCallBackFunction){
		var resourceURL = _appContextPath+"/ws/transportation/busstop/pickupdroppoints/add";
		return ajaxService.post(data, resourceURL, successCallBackFunction, errorCallBackFunction);
	}
});






