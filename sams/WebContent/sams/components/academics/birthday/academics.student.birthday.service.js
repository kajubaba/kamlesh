
samsApp.service('studentBirthdayService', function(ajaxService) {
	var resourceURL = _appContextPath+"/ws/acad/studentbirthdays";
	
	this.getTodaysBirthdays = function(successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/todays";
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
})










