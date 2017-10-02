
samsApp.service('annualFeeReportService', function(ajaxService) {

	this.getAnnualFeeReport = function(academicYearId, studentStatus, successCallBackFunction, errorCallBackFunction){
		
		var resourceURL = "";
		
		console.log(academicYearId);
		
		if(academicYearId != null && academicYearId != "null"){
			resourceURL = _appContextPath+"/ws/fee/report/annual?academicYearId="+academicYearId+"&studentStatus="+studentStatus;
		}else{
			resourceURL = _appContextPath+"/ws/fee/report/annual?studentStatus="+studentStatus;
		}
		
		return ajaxService.get(resourceURL, successCallBackFunction, errorCallBackFunction);
	}
	
	
	
})










