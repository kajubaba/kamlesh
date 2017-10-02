
samsApp.service('instituteSettingService', function(ajaxService) {

	var resourceURL = _appContextPath+"/ws/institute/setting";
	
	this.getSettings = function(successCallBackFunction, errorCallBackFunction){
		return ajaxService.get(resourceURL+"/get", successCallBackFunction, errorCallBackFunction);
	}
	
	this.saveSettings = function(data, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/save";
		return ajaxService.post(data, url, successCallBackFunction, errorCallBackFunction);
	}
	
})










