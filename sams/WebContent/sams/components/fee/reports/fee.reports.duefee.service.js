
samsApp.service('dueFeeReportService', function(ajaxService) {

	var resourceURL = _appContextPath+"/ws/feereport";
	
	this.getDueFeeReport = function(academicYearId, dueDate, admissionStatus, successCallBackFunction, errorCallBackFunction){
		
		
		
		var url = ""
		
		if(academicYearId != null){
			url = resourceURL+"/due?academicYearId="+academicYearId+"&admissionStatus="+admissionStatus;
			if(dueDate != null){
				url = url+"&dueDateStr="+dueDate;
			}
		}else{
			url = resourceURL+"/due?admissionStatus="+admissionStatus;
			if(dueDate != null){
				url = url+"&dueDateStr="+dueDate;
			}
		}

		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getDueStudentsOfClass = function(academicYearId, courseYearId, dueDate, admissionStatus, showOnlyDue, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/due/students?academicYearId="+academicYearId+"&courseYearId="+courseYearId+"&dueDateStr="+dueDate+"&admissionStatus="+admissionStatus+"&showOnlyDue="+showOnlyDue;
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getAllDueStudents = function(academicYearId, dueDate, studentStatus, showOnlyDue, successCallBackFunction, errorCallBackFunction){
		
		var url = "";
		
		if(academicYearId != null){
			url = resourceURL+"/due/allstudents?academicYearId="+academicYearId+"&studentStatus="+studentStatus+"&showOnlyDue="+showOnlyDue;
			if(dueDate != null){
				url = url+"&dueDateStr="+dueDate;
			}
			
			
			//url = resourceURL+"/due/allstudents?academicYearId="+academicYearId+"&dueDateStr="+dueDate;
		}else{
			//url = resourceURL+"/due/allstudents?dueDateStr="+dueDate;
			url = resourceURL+"/due/allstudents?studentStatus="+studentStatus;
			if(dueDate != null){
				url = url+"&dueDateStr="+dueDate;
			}
		}
		
		
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.generateDueFeeNotice = function(data, successCallBackFunction, errorCallBackFunction){
		return ajaxService.post(data, resourceURL+"/duefeenotice", successCallBackFunction, errorCallBackFunction);
	}
	
	this.getAllDueDates = function(academicYearId, successCallBackFunction, errorCallBackFunction){
		var url = "";
		if(academicYearId != null){
			url = resourceURL+"/duefee/duedates?academicYearId="+academicYearId;
		}else{
			url = resourceURL+"/duefee/duedates";
		}
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
})










