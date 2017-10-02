
samsApp.service('scoreCardService', function(ajaxService) {
	var resourceURL = _appContextPath+"/ws/academics/scorecard";
	
	this.getBlankScoreCard = function(examPatternId, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/sample/"+examPatternId;
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	
	
	
})










