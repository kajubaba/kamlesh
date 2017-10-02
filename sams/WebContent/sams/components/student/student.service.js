
samsApp.service('studentService', function(ajaxService) {

	var personalInfoResourceURL = "ws/student/personalinfo";
	var studentDocResourceURL = "ws/student/document";
	
	this.getStudentDetails = function(studentId, successCallBackFunction, errorCallBackFunction){
		var resourceURL = _appContextPath+"/ws/student/details/"+studentId;
		return ajaxService.get(resourceURL, successCallBackFunction, errorCallBackFunction);
	}
	
	this.saveOrUpdateGaurdianDetails = function(data, successCallBackFunction, errorCallBackFunction){
		var resourceURL = _appContextPath+"/ws/studentinfo/update/gaurdianinfo";
		return ajaxService.post(data, resourceURL, successCallBackFunction, errorCallBackFunction);
	}
	
	this.updateStudentPersonalInformation = function(data, successCallBackFunction, errorCallBackFunction){
		var resourceURL = _appContextPath+"/ws/studentinfo/update/personalinfo";
		return ajaxService.post(data, resourceURL, successCallBackFunction, errorCallBackFunction);
	}
	
	this.updateStudentParentsInformation = function(data, successCallBackFunction, errorCallBackFunction){
		var resourceURL = _appContextPath+"/ws/studentinfo/update/parentsinfo";
		return ajaxService.post(data, resourceURL, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getStudentBriedInfo = function(studentId, successCallBackFunction, errorCallBackFunction){
		var resourceURL = _appContextPath+"/ws/student/briefinfo/"+studentId;
		return ajaxService.get(resourceURL, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getStudentFeeBriefInfo = function(studentId, classId, successCallBackFunction, errorCallBackFunction){
		
		var resourceURL = _appContextPath+"/ws/student/feebriefinfo/"+studentId;
		
		if(classId != null){
			var resourceURL = resourceURL+"?classId="+classId;
		}
		
		return ajaxService.get(resourceURL, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getStudentBankDetails = function(studentId, successCallBackFunction, errorCallBackFunction){
		var resourceURL = _appContextPath+"/"+personalInfoResourceURL+"/"+studentId+"/bankdetails";
		return ajaxService.get(resourceURL, successCallBackFunction, errorCallBackFunction);
	}
	
	this.updateStudentBankDetails = function(data, successCallBackFunction, errorCallBackFunction){
		var resourceURL = _appContextPath+"/"+personalInfoResourceURL+"/update/bankdetails";
		return ajaxService.post(data, resourceURL, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getStudentDocuments = function(studentId, successCallBackFunction, errorCallBackFunction){
		var resourceURL = _appContextPath+"/"+studentDocResourceURL+"/getall?studentId="+studentId;
		return ajaxService.get(resourceURL, successCallBackFunction, errorCallBackFunction);
	}
	
	this.uploadStudentDocument = function(documentForm, successCallBackFunction, errorCallBackFunction){
		var resourceURL = _appContextPath+"/"+studentDocResourceURL+"/upload";
		return ajaxService.postWithFileInput(documentForm, resourceURL, successCallBackFunction, errorCallBackFunction);
	}
})










