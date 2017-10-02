
samsApp.service('academicSessionClassService', function(ajaxService) {

	var resourceURL = _appContextPath+"/ws/academic-session/class";
	
	
	this.getAcademicSessionClassesDetails = function(academicSessionId, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/list/"+academicSessionId;
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getAcademicSessionNotAddedCourses = function(academicSessionId, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/not-added-course/"+academicSessionId;
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.addCourseInAcademicSession = function(formData, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/add-course-in-academic-session";
		return ajaxService.post(formData, url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.updateCourseYearDetails = function(formData, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/update/course-year";
		return ajaxService.post(formData, url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getAcademicSessionClassDetails = function(courseYearSettingId, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/details/"+courseYearSettingId;
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getClassInstallments = function(courseYearSettingId, admissionTypeId, installmentCount, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/installments/"+courseYearSettingId+"/"+admissionTypeId+"?installments="+installmentCount;
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.saveAcademicSessionClassFee = function(formData, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/savefee";
		return ajaxService.post(formData, url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.saveFeeInstallments = function(formData, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/save/installments";
		return ajaxService.post(formData, url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.updateInstallmentDueDateAndLateFeeRule = function(formData, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/save/installmentsduedateandlatefeerule";
		return ajaxService.post(formData, url, successCallBackFunction, errorCallBackFunction);
	}
	
	
})










