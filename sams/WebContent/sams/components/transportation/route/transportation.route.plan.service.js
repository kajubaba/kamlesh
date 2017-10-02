

samsApp.service('routePlanService', function(ajaxService) {
	
	var resourceURL = _appContextPath+"/ws/transportation/routeplan";
	var vehicleRouteResourceURL = _appContextPath+"/ws/transportation/vehicleroute"; 
	
	this.getRouteBusStops = function(routeId, successCallBackFunction, errorCallBackFunction){
		return ajaxService.get(resourceURL+"/"+routeId+"/busstops", successCallBackFunction, errorCallBackFunction);
	}
	
	this.getBusStopsToBeAssignedToRoute = function(routeId, successCallBackFunction, errorCallBackFunction){
		return ajaxService.get(resourceURL+"/"+routeId+"/notassignedbusstops", successCallBackFunction, errorCallBackFunction);
	}
	
	this.importBusStopsInRoute = function(routeId, busStops, successCallBackFunction, errorCallBackFunction){
		return ajaxService.post(busStops, resourceURL+"/"+routeId+"/import", successCallBackFunction, errorCallBackFunction);
	}
	
	this.getStudentsOfRouteBusStop = function(routeBusStopId, successCallBackFunction, errorCallBackFunction){
		return ajaxService.get(resourceURL+"/busstop/"+routeBusStopId+"/students", successCallBackFunction, errorCallBackFunction);
	}
	
	this.getStudentsforRouteAssignment = function(routeBusStopId, successCallBackFunction, errorCallBackFunction){
		return ajaxService.get(resourceURL+"/students/notassignedttbusstop/"+routeBusStopId, successCallBackFunction, errorCallBackFunction);
	}
	
	this.assignStudentToRoute = function(routeBusStopId, students, successCallBackFunction, errorCallBackFunction){
		return ajaxService.post(students, resourceURL+"/assinstudentstoroute/"+routeBusStopId, successCallBackFunction, errorCallBackFunction);
	}
	
	this.removeRouteStudent = function(routeStudentId, routeBusStopId, successCallBackFunction, errorCallBackFunction){
		return ajaxService.get(resourceURL+"/removestudent/"+routeStudentId+"?routeBusStopId="+routeBusStopId, successCallBackFunction, errorCallBackFunction);
	}
	
	this.removeRouteBusStop = function(routeBusStopId, successCallBackFunction, errorCallBackFunction){
		return ajaxService.get(resourceURL+"/removebusstop/"+routeBusStopId, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getVehicleOnRoute = function(routeId, successCallBackFunction, errorCallBackFunction){
		return ajaxService.get(vehicleRouteResourceURL+"/vehicleonroute/"+routeId, successCallBackFunction, errorCallBackFunction);
	}
	
	this.updateRouteVehicle = function(routeId, academicSessionVehicleId, successCallBackFunction, errorCallBackFunction){
		return ajaxService.get(vehicleRouteResourceURL+"/update?routeId="+routeId+"&academicSessionVehicleId="+academicSessionVehicleId, successCallBackFunction, errorCallBackFunction);
	}
	
});






