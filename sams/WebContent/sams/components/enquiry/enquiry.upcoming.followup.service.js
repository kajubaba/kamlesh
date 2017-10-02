
samsApp.service('upcomingFollowupService', function(ajaxService) {

	var resourceURL = _appContextPath+"/ws/followups";
	
	
	this.getTodaysFollowupsCount = function(successCallBackFunction, errorCallBackFunction){
		return ajaxService.get(resourceURL+"/todays-count", successCallBackFunction, errorCallBackFunction);
	}
	
	this.getTodaysFollowups = function(successCallBackFunction, errorCallBackFunction){
		return ajaxService.get(resourceURL+"/todays", successCallBackFunction, errorCallBackFunction);
	}	
	
	this.getTomorrowsFollowups = function(successCallBackFunction, errorCallBackFunction){
		return ajaxService.get(resourceURL+"/tomorrows", successCallBackFunction, errorCallBackFunction);
	}
	
	this.getTomorrowsFollowupsCount = function(successCallBackFunction, errorCallBackFunction){
		return ajaxService.get(resourceURL+"/tomorrows-count", successCallBackFunction, errorCallBackFunction);
	}
	
			
})










