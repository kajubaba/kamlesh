

samsApp.service('routeService', function(ajaxService) {
	
	var resourceURL = _appContextPath+"/ws/transportation/route"; 
	
	this.getAllRoutes = function(successCallBackFunction, errorCallBackFunction){
		return ajaxService.get(resourceURL+"/allroutes", successCallBackFunction, errorCallBackFunction);
	}
	
	this.getRouteCount = function(successCallBackFunction, errorCallBackFunction){
		return ajaxService.get(resourceURL+"/count", successCallBackFunction, errorCallBackFunction);
	}
	
	this.addRoute = function(formData, successCallBackFunction, errorCallBackFunction){
		return ajaxService.post(formData, resourceURL+"/add", successCallBackFunction, errorCallBackFunction);
	}
	
	this.createFlipRoute = function(formData, successCallBackFunction, errorCallBackFunction){
		return ajaxService.post(formData, resourceURL+"/copy", successCallBackFunction, errorCallBackFunction);
	}
	
	this.getRouteDetail = function(routeId, successCallBackFunction, errorCallBackFunction){
		return ajaxService.get(resourceURL+"/"+routeId, successCallBackFunction, errorCallBackFunction);
	}
	
	this.updateRoute = function(formData, successCallBackFunction, errorCallBackFunction){
		return ajaxService.post(formData, resourceURL+"/update", successCallBackFunction, errorCallBackFunction);
	}
});






