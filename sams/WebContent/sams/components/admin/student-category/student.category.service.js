
samsApp.service('studentCategoryService', function(ajaxService) {

	var resourceURL = _appContextPath+"/ws/studentcategory";
	
	
	this.getStudentCategories = function(successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/list";
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getStudentCategory = function(studentCategoryId, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/get/"+studentCategoryId;
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.saveStudentCategory = function(formData, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/save";
		return ajaxService.post(formData, url, successCallBackFunction, errorCallBackFunction);
	}
	
	
	
})










