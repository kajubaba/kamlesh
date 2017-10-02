
/**
 * @author Narendra Patidar
 * @Date Jan-16-2016
 * 
 * This Angular JS based service that can be injected into controller and other services.
 * 
 * It is used to get students, student count who adopted bus facility
 * 
 */
samsApp.service('busAdoptedService', function(ajaxService) {

	
	/**
	 * Fetch list of students who adopted bus facility
	 * 
	 * @ studentStatusId - selected student status.
	 */
	this.getAdmissions = function(studentStatusId, academicYearId, successCallBackFunction, errorCallBackFunction){
		var resourceURL = _appContextPath+"/ws/admissions/busadopted/studentView/"+studentStatusId;
		if(academicYearId != null && academicYearId != "null"){
			resourceURL = resourceURL+"?ayid="+academicYearId;
		}
		return ajaxService.get(resourceURL, successCallBackFunction, errorCallBackFunction);
	}
	
	
	/**
	 * Fetch bus stop wise student count
	 * 
	 * @ studentStatusId - selected student status.
	 * 
	 */
	this.getBusStopWiseAdmissionCount = function(studentStatusId, academicYearId, successCallBackFunction, errorCallBackFunction){
		var resourceURL = _appContextPath+"/ws/admissions/busadopted/busstopview/"+studentStatusId;
		if(academicYearId != null && academicYearId != "null"){
			resourceURL = resourceURL+"?ayid="+academicYearId;
		}
		return ajaxService.get(resourceURL, successCallBackFunction, errorCallBackFunction);
	}
	
	
	/**
	 * Fetch class wise student count
	 * 
	 * @ studentStatusId - selected student status.
	 * 
	 */
	this.getClassWiseAdmissionCount = function(studentStatusId, academicYearId, successCallBackFunction, errorCallBackFunction){
		var resourceURL = _appContextPath+"/ws/admissions/busadopted/classview/"+studentStatusId;
		if(academicYearId != null && academicYearId != "null"){
			resourceURL = resourceURL+"?ayid="+academicYearId;
		}
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
		var resourceURL = _appContextPath+"/ws/admissions/busadopted/classwise/"+studentStatusId+"/"+classId;
		return ajaxService.get(resourceURL, successCallBackFunction, errorCallBackFunction);
	}
	
	/**
	 * Fetch bus facility adopted students for selected student status and bus stop
	 * 
	 * @ studentStatusId - selected student status.
	 * @ busStopId - selected bus stop.
	 * 
	 */
	this.getStudentByBusStop = function(studentStatusId, busStopId, academicYearId, successCallBackFunction, errorCallBackFunction){
		var resourceURL = _appContextPath+"/ws/admissions/busadopted/busstopwise/"+studentStatusId+"/"+busStopId;
		if(academicYearId != null && academicYearId != "null"){
			resourceURL = resourceURL+"?ayid="+academicYearId;
		}
		return ajaxService.get(resourceURL, successCallBackFunction, errorCallBackFunction);
	}
	
	
})










