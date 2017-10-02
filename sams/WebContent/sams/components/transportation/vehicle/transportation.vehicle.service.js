

samsApp.service('vehicleService', function(ajaxService) {
	
	var resourceURL = _appContextPath+"/ws/transportation/vehicle"; 
	
	this.getAllVehicles = function(successCallBackFunction, errorCallBackFunction){
		return ajaxService.get(resourceURL+"/allvehicles", successCallBackFunction, errorCallBackFunction);
	}
	
	this.addvehicle = function(formData, successCallBackFunction, errorCallBackFunction){
		return ajaxService.post(formData, resourceURL+"/add", successCallBackFunction, errorCallBackFunction);
	}
	
	this.getVehicleDetail = function(vehicleId, successCallBackFunction, errorCallBackFunction){
		return ajaxService.get(resourceURL+"/"+vehicleId, successCallBackFunction, errorCallBackFunction);
	}
	
	this.updateVehicle = function(formData, successCallBackFunction, errorCallBackFunction){
		return ajaxService.post(formData, resourceURL+"/update", successCallBackFunction, errorCallBackFunction);
	}
});






