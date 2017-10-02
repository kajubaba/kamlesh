
samsApp.service('enquiryListService', function(ajaxService) {

	var resourceURL = _appContextPath+"/ws/enquiry/list";
	
	
	this.getTodaysIssuedForms = function(successCallBackFunction, errorCallBackFunction){
		return ajaxService.get(resourceURL+"/todays-issued-form", successCallBackFunction, errorCallBackFunction);
	}
	
	this.getIssuedForms = function(fromDate, toDate, successCallBackFunction, errorCallBackFunction){
		return ajaxService.get(resourceURL+"/issued-form?fromDateStr="+fromDate+"&toDateStr="+toDate, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getTotalCount = function(successCallBackFunction, errorCallBackFunction){
		return ajaxService.get(resourceURL+"/count", successCallBackFunction, errorCallBackFunction);
	}	
	
	this.getIssuedFormCount = function(successCallBackFunction, errorCallBackFunction){
		return ajaxService.get(resourceURL+"/issuedFormCount", successCallBackFunction, errorCallBackFunction);
	}
	
	this.getHotCount = function(successCallBackFunction, errorCallBackFunction){
		return ajaxService.get(resourceURL+"/hotcount", successCallBackFunction, errorCallBackFunction);
	}
	
	this.getInProgressCount = function(successCallBackFunction, errorCallBackFunction){
		return ajaxService.get(resourceURL+"/inprogresscount", successCallBackFunction, errorCallBackFunction);
	}
	
	this.getCompletedCount = function(successCallBackFunction, errorCallBackFunction){
		return ajaxService.get(resourceURL+"/completedcount", successCallBackFunction, errorCallBackFunction);
	}
	
	this.getRecentEnquiries = function(successCallBackFunction, errorCallBackFunction){
		return ajaxService.get(resourceURL+"/recent", successCallBackFunction, errorCallBackFunction);
	}
	
	this.getEnquiries = function(academicSessionId, successCallBackFunction, errorCallBackFunction){
		return ajaxService.get(resourceURL+"/"+academicSessionId, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getClasswiseEnquiries = function(academicSessionId, successCallBackFunction, errorCallBackFunction){
		return ajaxService.get(resourceURL+"/classwise/"+academicSessionId, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getEnquiriesByClass = function(classId, successCallBackFunction, errorCallBackFunction){
		return ajaxService.get(resourceURL+"/byclass/"+classId, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getStatusWiseEnquiries = function(academicSessionId, successCallBackFunction, errorCallBackFunction){
		return ajaxService.get(resourceURL+"/statuswise/"+academicSessionId, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getEnquiriesByStatus = function(academicSessionId, statusId, successCallBackFunction, errorCallBackFunction){
		return ajaxService.get(resourceURL+"/"+academicSessionId+"/bystatus/"+statusId, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getEnquiriesByContactNo = function(contactNo, contactOf, successCallBackFunction, errorCallBackFunction){
		return ajaxService.get(resourceURL+"/by-contact?contactNo="+contactNo+"&contactOf="+contactOf, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getCities = function(successCallBackFunction, errorCallBackFunction){
		return ajaxService.get(resourceURL+"/cities", successCallBackFunction, errorCallBackFunction);
	}
	
})










