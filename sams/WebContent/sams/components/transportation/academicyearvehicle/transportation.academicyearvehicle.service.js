

samsApp.service('academicYearVehicleService', function(ajaxService) {
	
	var resourceURL = _appContextPath+"/ws/transportation/academicyearvehicle";
	
	this.getVehiclesInAcademicYear = function(successCallBackFunction, errorCallBackFunction){
		return ajaxService.get(resourceURL+"/allvehicles", successCallBackFunction, errorCallBackFunction);
	}
	
	this.getAcademicSessionVehicle = function(academicSessionVehicleId, successCallBackFunction, errorCallBackFunction){
		return ajaxService.get(resourceURL+"/"+academicSessionVehicleId, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getVehicleCountInAcademicYear = function(successCallBackFunction, errorCallBackFunction){
		return ajaxService.get(resourceURL+"/count", successCallBackFunction, errorCallBackFunction);
	}
	
	this.getVehicleToBeAssignedInAcademicYear = function(successCallBackFunction, errorCallBackFunction){
		return ajaxService.get(resourceURL+"/toBeAssigned", successCallBackFunction, errorCallBackFunction);
	}
	
	this.getVehicleSeatCapacity = function(successCallBackFunction, errorCallBackFunction){
		return ajaxService.get(resourceURL+"/seat-capacity", successCallBackFunction, errorCallBackFunction);
	}
	
	this.importVehiclesInAcademicYear = function(vehicles, successCallBackFunction, errorCallBackFunction){
		console.log(vehicles);
		
		return ajaxService.post(vehicles, resourceURL+"/import", successCallBackFunction, errorCallBackFunction);
	}
});






