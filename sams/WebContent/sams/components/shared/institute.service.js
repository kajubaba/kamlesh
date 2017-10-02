
samsApp.service('instituteService', function(ajaxService) {

	var resourceURL = "/ws/institute";
	
	this.getAllInstitutes = function(successCallBackFunction, errorCallBackFunction){
		return ajaxService.get(_appContextPath+""+resourceURL+"/list", successCallBackFunction, errorCallBackFunction);
	}
	
	this.switchInstitute = function(instituteId, successCallBackFunction, errorCallBackFunction){
		return ajaxService.get(_appContextPath+""+resourceURL+"/switchinstitute/"+instituteId, successCallBackFunction, errorCallBackFunction);
	}
	
	
})










