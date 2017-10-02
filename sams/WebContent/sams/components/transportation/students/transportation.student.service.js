

samsApp.service('transStudentService', function(ajaxService) {
	
	var resourceURL = _appContextPath+"/ws/transportation/busadopted";
	
	this.getBusAdoptedAdmissionCount = function(successCallBackFunction, errorCallBackFunction){
		var resourceURL = _appContextPath+"/ws/admission/dashboard/busAdopted";
		return ajaxService.get(resourceURL, successCallBackFunction, errorCallBackFunction);
	}
	
	/**
	 * Fetch list of students who adopted bus facility
	 * 
	 * @ studentStatusId - selected student status.
	 */
	this.getBusFacilityAdoptedStudents = function(studentStatusId, successCallBackFunction, errorCallBackFunction){
		return ajaxService.get(resourceURL+"/studentView/"+studentStatusId, successCallBackFunction, errorCallBackFunction);
	}
	
	/**
	 * Fetch bus stop wise student count
	 * 
	 * @ studentStatusId - selected student status.
	 * 
	 */
	this.getBusStopWiseAdmissionCount = function(studentStatusId,successCallBackFunction, errorCallBackFunction){
		var resourceURL = _appContextPath+"/ws/admissions/busadopted/busstopview/"+studentStatusId;
		return ajaxService.get(resourceURL, successCallBackFunction, errorCallBackFunction);
	}
	
	
	/**
	 * Fetch class wise student count
	 * 
	 * @ studentStatusId - selected student status.
	 * 
	 */
	this.getClassWiseAdmissionCount = function(studentStatusId,successCallBackFunction, errorCallBackFunction){
		var resourceURL = _appContextPath+"/ws/admissions/busadopted/classview/"+studentStatusId;
		return ajaxService.get(resourceURL, successCallBackFunction, errorCallBackFunction);
	}
	
	/**
	 * Fetch bus facility adopted students for selected student status and class
	 * 
	 * @ studentStatusId - selected student status.
	 * @ classId - selected class.
	 * 
	 */
	this.getStudentByClass = function(studentStatusId, classId, successCallBackFunction, errorCallBackFunction){
		return ajaxService.get(resourceURL+"/classwise/"+studentStatusId+"/"+classId, successCallBackFunction, errorCallBackFunction);
	}
	
	/**
	 * Fetch bus facility adopted students for selected student status and bus stop
	 * 
	 * @ studentStatusId - selected student status.
	 * @ busStopId - selected bus stop.
	 * 
	 */
	this.getStudentByBusStop = function(studentStatusId, busStopId, successCallBackFunction, errorCallBackFunction){
		//var resourceURL = _appContextPath+"/ws/admissions/busadopted/busstopwise/"+studentStatusId+"/"+busStopId;
		return ajaxService.get(resourceURL+"/busstopwise/"+studentStatusId+"/"+busStopId, successCallBackFunction, errorCallBackFunction);
	}
});






