

samsApp.service('vehicleDriverService', function(ajaxService) {

	var resourceURL = _appContextPath+"/ws/vehicle/drivers";
	
	
	this.getdriverList = function(successCallBackFunction, errorCallBackFunction){
		return ajaxService.get(resourceURL, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getDriver = function(driverId, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/"+driverId;
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.addDriver = function(formData, successCallBackFunction, errorCallBackFunction){
		return ajaxService.post(formData, resourceURL, successCallBackFunction, errorCallBackFunction);
	}
	
	this.updateDriver = function(formData, successCallBackFunction, errorCallBackFunction){
		return ajaxService.put(formData, resourceURL, successCallBackFunction, errorCallBackFunction);
	}
	
})











