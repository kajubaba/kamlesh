
/* 
 *   Server Resource : com.narendra.sams.web.restws.fee.com.narendra.sams.web.restws.fee 
 *   Resource URI : /ws/fee/adjusted/students
 *   
 */

samsApp.service('feeAdjustedStudentService', function(ajaxService) {

	var serverResourceURL = _appContextPath+"/ws/fee/adjusted/students";
	
	
	this.getFeeAdjustedStudentCount = function(successCallBackFunction, errorCallBackFunction){
		var resourceURL = serverResourceURL+"/count";
		return ajaxService.get(resourceURL, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getFeeAdjustedStudents = function(academicYearId, successCallBackFunction, errorCallBackFunction){
		
		var resourceURL = serverResourceURL+"/list";
		
		if(academicYearId != null && academicYearId != "null"){
			resourceURL = resourceURL+"?academicYearId="+academicYearId;
		}
		
		return ajaxService.get(resourceURL, successCallBackFunction, errorCallBackFunction);
	}
	
})










